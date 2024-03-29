package com.bpdsulteng.mobile.ui.main.chat

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.bpdsulteng.mobile.model.Chatlist
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.bpdsulteng.mobile.utils.CommonUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONObject

class ChatViewModel : BaseObservableViewModel<ChatNavigator>() {
    val fuser by lazy { FirebaseAuth.getInstance().currentUser }
    val referenceChatList by lazy { FirebaseDatabase.getInstance().getReference("Chatlist").child(fuser!!.uid) }
    val referenceUser by lazy { FirebaseDatabase.getInstance().getReference("Users") }
    private val usersList = ObservableArrayList<Chatlist>()
    private val mUsers = ObservableArrayList<User>()

    @Bindable
    fun getUsers() = mUsers

    fun fetchChatUserList() {
        referenceChatList.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                usersList.clear()
                for (snapshot in dataSnapshot.children) {
                    Log.d("DataSnapshot", snapshot.toString())
                    val chatlist = snapshot.getValue(Chatlist::class.java)
                    usersList.add(chatlist)
                }
                chatList()
            }
        })
        updateToke()
    }

    private fun chatList() {
        referenceUser.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mUsers.clear()
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.value is Map<*, *>) {
                        val values = snapshot.value as Map<String, Any>?
                        val response = JSONObject(values)
                        Log.d("DataSnapshot", snapshot.toString())
                        Log.d("JSONObject", response.toString())

                        val user = CommonUtils.mto(response.toString(), User::class.java)
                        for (chatlist in usersList) {
                            if (user?.id.equals(chatlist.id)) {
                                mUsers.add(user)
                                notifyPropertyChanged(BR.users)
                            }
                        }
                    }
                }
            }
        })
    }

    fun updateToke() {
        //todo update token for notification
    }
}