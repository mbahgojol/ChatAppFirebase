package com.bpdsulteng.mobile.ui.main.chat

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ItemUsersBinding
import com.bpdsulteng.mobile.model.Chat
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.utils.click
import com.bpdsulteng.mobile.utils.gone
import com.bpdsulteng.mobile.utils.visible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserAdapter(private val mContext: Context,
                  private val ischat: Boolean,
                  private val mUsers: MutableList<User> = arrayListOf())
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listener: ((User) -> Unit)? = null

    fun setListener(listener: (User) -> Unit) {
        this.listener = listener
    }

    fun clearItems() {
        this.mUsers.clear()
    }

    fun addItems(items: List<User>) {
        this.mUsers.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mUsers.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemUsersBinding>(LayoutInflater.from(mContext), R.layout.item_users, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = mUsers[position]
        holder.bind(user)

        if (ischat) {
            user.id?.let { holder.lastMessage(it) }
        } else {
            holder.binding.lastMsg.gone()
        }

        if (ischat) {
            if (user.status == "online") {
                holder.binding.imgOn.visible()
                holder.binding.imgOff.gone()
            } else {
                holder.binding.imgOn.gone()
                holder.binding.imgOff.visible()
            }
        } else {
            holder.binding.imgOn.gone()
            holder.binding.imgOff.gone()
        }

        holder.itemView.click {
            listener.let { it?.invoke(user) }
        }
    }


    inner class ViewHolder(var binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.username.text = user.username
            if (user.imageURL == "default") {
                binding.profileImage.setImageResource(R.mipmap.ic_launcher)
            } else {
                //todo set image with gradle
                //Glide.with(mContext).load(user.imageURL).into(binding.profileImage)
            }
        }

        fun lastMessage(userid: String) {
            var theLastMessage = "default"
            val firebaseUser = FirebaseAuth.getInstance().currentUser
            val reference = FirebaseDatabase.getInstance().getReference("Chats")
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        if (snapshot.key.toString().contains("-")) {
                            val chat = snapshot.getValue(Chat::class.java)
                            if (firebaseUser != null && chat != null) {
                                if (chat.receiver == firebaseUser.uid
                                        && chat.sender == userid
                                        || chat.receiver == userid
                                        && chat.sender == firebaseUser.uid) {
                                    theLastMessage = chat.message.toString()
                                }
                            }
                        }
                    }
                    when (theLastMessage) {
                        "default" -> binding.lastMsg.text = "No Message"
                        else -> binding.lastMsg.text = theLastMessage
                    }
                    theLastMessage = "default"
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        }
    }
}
