package me.yangcx.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleEndedException
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.LifecycleScopes
import io.reactivex.CompletableSource
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleScopeProvider<ViewHolderEvent> {
	companion object {
		private val CORRESPONDING_EVENTS = CorrespondingEventsFunction<ViewHolderEvent> { viewHolderEvent ->
			when (viewHolderEvent) {
				ViewHolderEvent.ON_CREATE -> ViewHolderEvent.ON_DESTROY
				else -> throw LifecycleEndedException(
						"Cannot use ViewHolder lifecycle after unbind."
													 )
			}
		}
	}

	private val lifecycleEvents by lazy {
		BehaviorSubject.createDefault(ViewHolderEvent.ON_CREATE)
	}

	override fun lifecycle(): Observable<ViewHolderEvent> = lifecycleEvents.hide()

	override fun correspondingEvents(): CorrespondingEventsFunction<ViewHolderEvent> = CORRESPONDING_EVENTS

	override fun peekLifecycle(): ViewHolderEvent? = lifecycleEvents.value

	override fun requestScope(): CompletableSource = LifecycleScopes.resolveScopeFromLifecycle(this)

	fun onDestroy() {
		lifecycleEvents.onNext(ViewHolderEvent.ON_DESTROY)
	}
}