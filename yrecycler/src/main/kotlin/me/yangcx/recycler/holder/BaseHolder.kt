package me.yangcx.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.LifecycleScopes
import io.reactivex.CompletableSource
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class BaseHolder<T : Any>(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleScopeProvider<ViewHolderEvent> {
	companion object {
		private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewHolderEvent> { _ ->
			ViewHolderEvent.ON_DETACHED
		}
	}

	lateinit var data: T

	private val lifecycleEvents by lazy {
		BehaviorSubject.createDefault(ViewHolderEvent.ON_ATTACHED)
	}

	override fun lifecycle(): Observable<ViewHolderEvent> = lifecycleEvents.hide()

	override fun correspondingEvents(): CorrespondingEventsFunction<ViewHolderEvent> = CORRESPONDING_EVENTS

	override fun peekLifecycle(): ViewHolderEvent? = lifecycleEvents.value

	override fun requestScope(): CompletableSource = LifecycleScopes.resolveScopeFromLifecycle(this)

	internal fun onAttached() {
		lifecycleEvents.onNext(ViewHolderEvent.ON_ATTACHED)
	}

	internal fun onDetached() {
		lifecycleEvents.onNext(ViewHolderEvent.ON_DETACHED)
	}
}