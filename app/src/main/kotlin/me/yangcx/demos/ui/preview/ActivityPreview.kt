package me.yangcx.demos.ui.preview

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.activity_preview.*
import me.drakeet.multitype.MultiTypeAdapter
import me.drakeet.multitype.register
import me.yangcx.common.extend.click
import me.yangcx.demos.R
import me.yangcx.demos.base.BaseActivity
import me.yangcx.preview.entity.ImageData
import me.yangcx.preview.entity.ImageShowType
import me.yangcx.preview.entity.PreviewFinishedEvent
import me.yangcx.preview.entity.PreviewStartEvent
import me.yangcx.preview.ui.ActivitySingleImagePreview
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.startActivity

/**
 * 预览展示界面
 * create by 97457
 * create at 2018/12/06 0006
 */
class ActivityPreview : BaseActivity(R.layout.activity_preview) {
    companion object {
        fun launch(context: Context) {
            context.startActivity<ActivityPreview>()
        }
    }

    private val postingTag by lazy {
        "05zBAT8OHl0xZ50A"
    }
    private val dataList by lazy {
        (1..29).map {
            ImageData(
                "http://45.32.19.133/WebImages/thumbnail/Image$it.webp",
                "http://45.32.19.133/WebImages/origin/Image$it.webp",
                ImageShowType.JUST_THUMBNAIL
            )
        }
    }

    private val startData by lazy {
        dataList.random()
    }
    private val endData by lazy {
        dataList.random()
    }
    private val adapter by lazy {
        MultiTypeAdapter()
    }

    private val layoutManager by lazy {
        FlexboxLayoutManager(this).apply {
            alignItems = AlignItems.STRETCH
        }
    }

    override fun initAfterUi() {
        EventBus.getDefault().register(this)
        loadTop()
        initRecycler()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    private fun loadTop() {
        Glide.with(this)
            .load(startData.thumbnailData)
            .apply(
                RequestOptions()
                    .centerCrop()
            ).into(ivPreviewStart)

        Glide.with(this)
            .load(endData.thumbnailData)
            .apply(
                RequestOptions()
                    .circleCrop()
            ).into(ivPreviewEnd)
    }

    override fun onBindViewListener() {
        click(ivPreviewStart, ivPreviewEnd)
            .subscribe {
                when (it) {
                    ivPreviewStart -> {
                        ActivitySingleImagePreview.launch(
                            this,
                            startData,
                            ivPreviewStart,
                            postingTag
                        )
                    }
                    ivPreviewEnd -> {
                        ActivitySingleImagePreview.launch(
                            this,
                            endData,
                            ivPreviewEnd,
                            postingTag
                        )
                    }
                }
            }
    }

    private fun initRecycler() {
        adapter.register(ImageData::class, BinderImage())
        rvImage.adapter = adapter
        rvImage.layoutManager = layoutManager
        adapter.items = dataList
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPreviewStart(previewStartEvent: PreviewStartEvent) {
        if (previewStartEvent.checkTag(postingTag)) {
            when (previewStartEvent.targetId) {
                ivPreviewStart.id -> {
                    ivPreviewStart.visibility = View.INVISIBLE
                }
                ivPreviewEnd.id -> {
                    ivPreviewEnd.visibility = View.INVISIBLE
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPreviewFinished(previewFinishedEvent: PreviewFinishedEvent) {
        if (previewFinishedEvent.checkTag(postingTag)) {
            when (previewFinishedEvent.targetId) {
                ivPreviewStart.id -> {
                    ivPreviewStart.visibility = View.VISIBLE
                }
                ivPreviewEnd.id -> {
                    ivPreviewEnd.visibility = View.VISIBLE
                }
            }
        }
    }
}