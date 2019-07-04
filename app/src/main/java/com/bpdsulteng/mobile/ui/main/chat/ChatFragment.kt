package com.bpdsulteng.mobile.ui.main.chat


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bpdsulteng.mobile.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.FragmentChatBinding
import com.bpdsulteng.mobile.ui.base.BaseFragment
import com.bpdsulteng.mobile.ui.chatroom.ChatRoomActivity
import kotlinx.android.synthetic.main.fragment_chat.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class ChatFragment : BaseFragment<FragmentChatBinding, ChatViewModel>(), ChatNavigator {
    private lateinit var binding: FragmentChatBinding
    @Inject
    internal lateinit var viewModel: ChatViewModel
    @Inject
    internal lateinit var adapterUser: UserAdapter

    override fun getBindingVariable() = BR.vmchatfragment
    override fun getLayoutId() = R.layout.fragment_chat
    override fun getViewModel() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding

        adapterUser.setListener {
            startActivity<ChatRoomActivity>("userid" to it.id)
        }
        recycler_view.apply {
            layoutManager = LinearLayoutManager(baseActivity)
            setHasFixedSize(true)
            adapter = adapterUser
        }
        viewModel.fetchChatUserList()
    }
}
