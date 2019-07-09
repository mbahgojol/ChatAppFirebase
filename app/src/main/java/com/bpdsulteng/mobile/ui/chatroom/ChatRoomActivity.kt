package com.bpdsulteng.mobile.ui.chatroom

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ActivityChatRoomBinding
import com.bpdsulteng.mobile.ui.base.BaseActivity
import com.bpdsulteng.mobile.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_chat_room.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import javax.inject.Inject

class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding, ChatRoomViewModel>(), ChatRoomNavigator {
    private lateinit var binding: ActivityChatRoomBinding
    @Inject
    internal lateinit var viewModel: ChatRoomViewModel
    @Inject
    internal lateinit var chatRoomAdapter: ChatRoomAdapter

    override fun getBindingVariable() = BR.vmchatroom
    override fun getLayoutId() = R.layout.activity_chat_room
    override fun getViewModel() = viewModel
    override fun getUserId() = intent.extras.getString("userid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding
        viewModel.navigator = this

        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            startActivity(intentFor<MainActivity>().clearTop())
        }

        recycler_view.apply {
            setHasFixedSize(true)
            var linearLayoutManager = LinearLayoutManager(this@ChatRoomActivity)
            linearLayoutManager.stackFromEnd = true
            layoutManager = linearLayoutManager
            adapter = chatRoomAdapter
            recycler_view.viewTreeObserver.addOnGlobalLayoutListener(this@ChatRoomActivity::scrollToEnd)
        }

        viewModel.configurationChatRoom()
    }

    fun scrollToEnd() {
        (chatRoomAdapter.itemCount - 1).takeIf { it > 0 }?.let(recycler_view::smoothScrollToPosition)
    }

    override fun showMsg(msg: String) {
        toast(msg)
    }

    private fun currentUser(userid: String) {
        val editor = getSharedPreferences("PREFS", Context.MODE_PRIVATE).edit()
        editor.putString("currentuser", userid)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateStatus("online")
        currentUser(getUserId())
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeEventListener()
        viewModel.updateStatus("offline")
        currentUser("none")
    }
}
