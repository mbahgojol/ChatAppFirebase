package com.bpdsulteng.mobile.ui.chatroom

interface ChatRoomNavigator {
    fun getUserId(): String
    fun showMsg(msg: String)
}