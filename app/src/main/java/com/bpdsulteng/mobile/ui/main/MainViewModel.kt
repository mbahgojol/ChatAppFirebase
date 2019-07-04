package com.bpdsulteng.mobile.ui.main

import android.databinding.Bindable
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.model.Chat
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.bpdsulteng.mobile.ui.main.chat.ChatFragment
import com.bpdsulteng.mobile.ui.main.profile.ProfileFragment
import com.bpdsulteng.mobile.ui.main.user.UserFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList
import kotlin.collections.HashMap

class MainViewModel
    : BaseObservableViewModel<MainNavigator>() {

    val firebaseUser by lazy { FirebaseAuth.getInstance().currentUser }
    val referenceUsers by lazy { FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser!!.uid) }
    val referenceChats by lazy { FirebaseDatabase.getInstance().getReference("Chats") }

    private val username = ObservableField<String>("")
    private val imgUrl = ObservableField<String>("")
    private val resId = ObservableInt(0)

    @Bindable
    fun getUsername() = username.get()

    @Bindable
    fun getImgUrl() = imgUrl.get()

    @Bindable
    fun getResId() = resId.get()

    fun fetchUsers() {
        referenceUsers.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                Log.d("SnapshotUser", dataSnapshot.value.toString())
                username.set(user?.username)
                notifyPropertyChanged(BR.username)
                if (user?.imageURL == "default") {
                    resId.set(R.mipmap.ic_launcher)
                    notifyPropertyChanged(BR.resId)
                } else {
                    imgUrl.set(user?.imageURL)
                    notifyPropertyChanged(BR.imgUrl)
                }
            }
        })
    }

    fun fetchChats(fm: FragmentManager, listerner: (ViewPagerAdapter) -> Unit) {
        referenceChats.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var adapter = ViewPagerAdapter(fm)
                var unread = 0
                for (snapshot in dataSnapshot.children) {
                    Log.d("SnapshotChat", snapshot.toString())
                    if (snapshot.key.toString().contains("-")) {
                        val chat = snapshot.getValue(Chat::class.java)
                        Log.d("receiver", chat?.receiver)
                        Log.d("firebaseUser", firebaseUser?.uid)
                        if (chat?.receiver.equals(firebaseUser?.uid) && !chat?.isIsseen!!) {
                            unread++
                        }
                    }
                }

                if (unread == 0) {
                    adapter.addFragment(ChatFragment(), "Chats")
                } else {
                    adapter.addFragment(ChatFragment(), "($unread)Chats")
                }
                adapter.addFragment(UserFragment(), "Users")
                adapter.addFragment(ProfileFragment(), "Profile")
                listerner.invoke(adapter)
            }
        })
    }

    fun updateStatus(status: String) {
        var params = HashMap<String, Any>().apply {
            put("status", status)
        }
        referenceUsers.updateChildren(params)
    }

    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }
}


class ViewPagerAdapter(fm: FragmentManager,
                       val fragments: ArrayList<Fragment> = arrayListOf(),
                       val titles: ArrayList<String> = arrayListOf()) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
    override fun getPageTitle(position: Int) = titles[position]
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}