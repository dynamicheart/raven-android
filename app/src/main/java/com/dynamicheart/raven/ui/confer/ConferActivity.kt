package com.dynamicheart.raven.ui.confer

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.avos.avoscloud.im.v2.AVIMConversation
import com.avos.avoscloud.im.v2.AVIMMessage
import com.avos.avoscloud.im.v2.Conversation
import com.dynamicheart.raven.R
import com.dynamicheart.raven.data.model.ref.UserRef
import com.dynamicheart.raven.ui.base.BaseActivity
import com.dynamicheart.raven.util.leancloud.widget.AVInputBottomBar
import javax.inject.Inject

class ConferActivity: BaseActivity(), ConferContract.View {

    @Inject lateinit var presenter: ConferPresenter
    @Inject lateinit var imMessagesAdapter: ImMessagesAdapter

    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.swipe_layout) lateinit var swipeLayout: SwipeRefreshLayout
    @BindView(R.id.recycler_view) lateinit var recyclerView: RecyclerView
    @BindView(R.id.input_bottom_bar) lateinit var inputBottomBar: AVInputBottomBar
    lateinit var linearLayoutManger: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        setContentView(R.layout.activity_confer)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)

        linearLayoutManger = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManger
        recyclerView.adapter = imMessagesAdapter
        swipeLayout.setOnRefreshListener {
            presenter.reFetchMessage(imMessagesAdapter)
        }

        presenter.attachView(this)
        presenter.getConversation()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showConversation(conversation: AVIMConversation) {
        swipeLayout.isEnabled = true
        inputBottomBar.tag = conversation.conversationId
        presenter.fetchMessage()
    }

    override fun showMessages(list: List<AVIMMessage>) {
        imMessagesAdapter.setMessageList(list)
        recyclerView.adapter = imMessagesAdapter
        imMessagesAdapter.notifyDataSetChanged()
        scrollToBottom()
    }

    override fun showRefreshedMessages(list: List<AVIMMessage>) {

    }

    override fun stopRefreshing() {
        swipeLayout.isRefreshing = false
    }

    private fun scrollToBottom() {
        linearLayoutManger.scrollToPositionWithOffset(imMessagesAdapter.getItemCount() - 1, 0)
    }
}