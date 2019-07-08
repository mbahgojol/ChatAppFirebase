package com.bpdsulteng.mobile.ui.chatroom

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.model.Chat
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class ChatRoomViewModel : BaseObservableViewModel<ChatRoomNavigator>() {
    private val fuser by lazy { FirebaseAuth.getInstance().currentUser }
    private var reference: DatabaseReference? = null
    private var seenListener: ValueEventListener? = null
    private val username = ObservableField<String>()
    private val resId = ObservableInt(0)
    private val imgUrl = ObservableField<String>("")
    private val mchat = ObservableArrayList<Chat>()
    private val notify = false
    private val textMsg = ObservableField<String>("")

    private fun setUsename(username: String) {
        this.username.set(username)
        notifyPropertyChanged(BR.username)
    }

    @Bindable
    fun getChat() = mchat

    @Bindable
    fun getUsername() = username.get()

    @Bindable
    fun getResId() = resId

    @Bindable
    fun getImgUrl() = imgUrl.get()

    @Bindable
    fun getMessage() = textMsg.get().toString()

    fun getTextMsg() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            textMsg.set(p0.toString())
        }
    }

    fun sendClicked() {
        if (textMsg.get().isNullOrEmpty()) {
            navigator.showMsg("You can't send empty message")
        } else {
            fuser?.uid?.let { sendMessage(it, navigator.getUserId(), textMsg.get().toString()) }
        }
        textMsg.set("")
        notifyPropertyChanged(BR.message)
    }

    fun configurationChatRoom() {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(navigator.getUserId())
        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                user?.username?.let { setUsename(it) }
                if (user?.imageURL.equals("default")) {
                    resId.set(R.mipmap.ic_launcher)
                    notifyPropertyChanged(BR.resId)
                } else {
                    imgUrl.set(user?.imageURL)
                    notifyPropertyChanged(BR.imgUrl)
                }

                user?.imageURL?.let { it1 ->
                    fuser?.uid?.let { it2 ->
                        readMessages(it2, navigator.getUserId(), it1)
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        seenMessage(navigator.getUserId())
    }

    fun readMessages(myid: String, userId: String, imageurl: String) {
        reference = FirebaseDatabase.getInstance().getReference("Chats")
        reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mchat.clear()
                for (snapshot in dataSnapshot.children) {
                    Log.d("DataSnapshot", snapshot.toString())
                    if (snapshot.key.toString().matches("-(.*)".toRegex())) {
                        val chat = snapshot.getValue(Chat::class.java)
                        if (chat?.receiver.equals(myid)
                                && chat?.sender.equals(userId)
                                || chat?.receiver.equals(userId)
                                && chat?.sender.equals(myid)) {
                            chat?.imageurl = imageurl
                            mchat.add(chat)
                        }
                    }
                }
                notifyPropertyChanged(BR.chat)
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun sendMessage(sender: String, receiver: String, message: String) {
        var reference = FirebaseDatabase.getInstance().reference
        val hashMap = HashMap<String, Any>()
        hashMap["sender"] = sender
        hashMap["receiver"] = receiver
        hashMap["message"] = message
        hashMap["isseen"] = false
        reference.child("Chats").push().setValue(hashMap)

        // add user to chat fragment
        val chatRef = fuser?.uid?.let {
            FirebaseDatabase.getInstance().getReference("Chatlist")
                    .child(it)
                    .child(navigator.getUserId())
        }

        chatRef?.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef.child("id").setValue(navigator.getUserId())
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

        val chatRefReceiver = fuser?.uid?.let {
            FirebaseDatabase.getInstance().getReference("Chatlist")
                    .child(navigator.getUserId())
                    .child(it)
        }
        chatRefReceiver?.child("id")?.setValue(fuser?.uid)

        reference = fuser?.uid?.let { FirebaseDatabase.getInstance().getReference("Users").child(it) }!!
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                /*if (notify) {
                    sendNotifiaction(receiver, user.getUsername(), message)
                }
                notify = false*/
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    fun seenMessage(userId: String) {
        reference = FirebaseDatabase.getInstance().getReference("Chats")
        seenListener = reference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.key.toString().contains("-")) {
                        val chat = snapshot.getValue(Chat::class.java)
                        if (chat?.receiver.equals(fuser?.uid) && chat?.sender.equals(userId)) {
                            val hashMap = HashMap<String, Any>()
                            hashMap["isseen"] = true
                            snapshot.ref.updateChildren(hashMap)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    fun sendNotification() {

    }

    fun updateStatus(status: String) {
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser!!.uid)
        val hashMap = HashMap<String, Any>()
        hashMap["status"] = status
        reference?.updateChildren(hashMap)
    }

    fun removeEventListener() {
        seenListener?.let { reference?.removeEventListener(it) }
    }
}