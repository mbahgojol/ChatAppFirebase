package com.bpdsulteng.mobile.ui.chatroom

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bpdsulteng.mobile.R
import com.bpdsulteng.mobile.databinding.ChatItemLeftBinding
import com.bpdsulteng.mobile.databinding.ChatItemRightBinding
import com.bpdsulteng.mobile.model.Chat
import com.bpdsulteng.mobile.utils.gone
import com.bpdsulteng.mobile.utils.visible
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ChatRoomAdapter(val context: Context, var mChat: MutableList<Chat> = arrayListOf()) : RecyclerView.Adapter<ChatRoomAdapter.ViewHolder>() {
    val MSG_TYPE_LEFT = 0
    val MSG_TYPE_RIGHT = 1

    private var fuser: FirebaseUser? = null

    fun clearItems() {
        mChat.clear()
    }

    fun addItems(items: List<Chat>) {
        this.mChat.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            MSG_TYPE_LEFT -> {
                var binding = DataBindingUtil.inflate<ChatItemLeftBinding>(LayoutInflater.from(context), R.layout.chat_item_left, parent, false)
                ViewHolder(binding)
            }
            else -> {
                var binding = DataBindingUtil.inflate<ChatItemRightBinding>(LayoutInflater.from(context), R.layout.chat_item_right, parent, false)
                ViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = mChat.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var chat = mChat[position]
        holder.bind(position, chat)
    }

    override fun getItemViewType(position: Int): Int {
        fuser = FirebaseAuth.getInstance().currentUser
        return if (mChat[position].sender.equals(fuser?.uid)) {
            MSG_TYPE_RIGHT
        } else {
            MSG_TYPE_LEFT
        }
    }

    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, chat: Chat) {
            if (binding is ChatItemRightBinding) {
                binding.showMessage.text = chat.message
                if (chat.imageurl.equals("default")) {
                    binding.profileImage.setImageResource(R.mipmap.ic_launcher)
                } else {
                    // todo implement set image with glide
                    /*Glide.with(mContext).load(imageurl).into(holder.profile_image);*/
                }
                if (position == mChat.size - 1) {
                    binding.txtSeen.visible()
                    if (chat.isIsseen) {
                        binding.txtSeen.text = "Seen"
                    } else {
                        binding.txtSeen.text = "Delivered"
                    }
                } else {
                    binding.txtSeen.gone()
                }
            } else if (binding is ChatItemLeftBinding) {
                binding.showMessage.text = chat.message
                if (chat.imageurl.equals("default")) {
                    binding.profileImage.setImageResource(R.mipmap.ic_launcher)
                } else {
                    // todo implement set image with glide
                    /*Glide.with(mContext).load(imageurl).into(holder.profile_image);*/
                }
                if (position == mChat.size - 1) {
                    if (chat.isIsseen) {
                        binding.txtSeen.text = "Seen"
                    } else {
                        binding.txtSeen.text = "Delivered"
                    }
                } else {
                    binding.txtSeen.gone()
                }
            }
        }
    }
}
