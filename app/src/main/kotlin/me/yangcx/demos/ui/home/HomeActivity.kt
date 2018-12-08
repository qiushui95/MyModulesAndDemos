package me.yangcx.demos.ui.home

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import me.drakeet.multitype.MultiTypeAdapter
import me.yangcx.demos.R
import me.yangcx.demos.base.ViewModelActivity
import me.yangcx.demos.entity.HomeButtonInfo
import me.yangcx.demos.entity.PostEvent
import me.yangcx.demos.varia.IEventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class HomeActivity : ViewModelActivity(R.layout.activity_home), IEventBus {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(ViewModelHome::class.java)
    }
    private val adapter by lazy {
        MultiTypeAdapter()
    }

    override fun initAfterUi() {
        initRecycler()
    }

    override fun onBindViewListener() {
        srlHome.setOnRefreshListener {
            srlHome.isRefreshing = false
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBindViewModel() {
        adapter.items = viewModel.buttonList
    }

    private fun initRecycler() {
        adapter.register(HomeButtonInfo::class.java, BinderHomeButton(this))
        rvHome.layoutManager = FlexboxLayoutManager(this)
        rvHome.adapter = adapter
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onItemClick(postEvent: PostEvent<HomeButtonInfo<Activity>>) {
        startActivity(Intent(this, postEvent.data.clickClass.java))
    }
}
