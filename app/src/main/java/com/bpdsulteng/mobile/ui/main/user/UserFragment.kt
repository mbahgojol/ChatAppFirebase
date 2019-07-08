package com.bpdsulteng.mobile.ui.main.user


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.FragmentUserBinding
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.base.BaseFragment
import com.bpdsulteng.mobile.ui.chatroom.ChatRoomActivity
import com.bpdsulteng.mobile.ui.main.chat.UserAdapter
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(), UserNavigator {
    private lateinit var binding: FragmentUserBinding
    @Inject
    internal lateinit var viewModel: UserViewModel
    @Inject
    internal lateinit var userAdapter: UserAdapter

    override fun getBindingVariable() = BR.vmuserfragment
    override fun getLayoutId() = R.layout.fragment_user
    override fun getViewModel() = viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = viewDataBinding

        etSearch.addTextChangedListener(viewModel.getSearchUsers())

        rvUserList.apply {
            layoutManager = LinearLayoutManager(baseActivity)
            setHasFixedSize(true)
            adapter = userAdapter
            userAdapter.setListener {
                startActivity<ChatRoomActivity>("userid" to it.id)
            }
        }

        viewModel.readUsers()
    }

    override fun onSuccesFetchUsers(items: List<User>) {
        userAdapter.clearItems()
        userAdapter.addItems(items)
    }
}
