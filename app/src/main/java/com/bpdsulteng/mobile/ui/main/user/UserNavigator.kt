package com.bpdsulteng.mobile.ui.main.user

import com.bpdsulteng.mobile.model.User

interface UserNavigator {
    fun onSuccesFetchUsers(items: List<User>)
}
