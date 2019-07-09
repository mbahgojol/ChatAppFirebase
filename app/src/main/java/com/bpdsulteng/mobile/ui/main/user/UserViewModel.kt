package com.bpdsulteng.mobile.ui.main.user

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.text.Editable
import android.text.TextWatcher
import com.android.databinding.library.baseAdapters.BR
import com.bpdsulteng.mobile.model.User
import com.bpdsulteng.mobile.ui.base.BaseObservableViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserViewModel : BaseObservableViewModel<UserNavigator>() {
    private val mUsers = ObservableArrayList<User>()
    private val searchUsers = ObservableField<String>()

    fun getSearchUsers() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            searchUsers(searchUsers.get().toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            searchUsers.set(p0.toString())
        }
    }

    @Bindable
    fun getUsersList() = mUsers as List<User>

    fun readUsers() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance().getReference("Users")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mUsers.clear()
                for (snapshot in dataSnapshot.children) {
                    if (snapshot.getValue(User::class.java) != null) {
                        val user = snapshot.getValue(User::class.java)
                        if (user?.id != null) {
                            if (!user.id.equals(firebaseUser?.uid)) {
                                mUsers.add(user)
                            }
                        }
                    }
                }
                notifyPropertyChanged(BR.usersList)
            }
        })
    }

    fun searchUsers(s: String) {
        val fuser = FirebaseAuth.getInstance().currentUser
        val query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("search")
                .startAt(s)
                .endAt("s \uf8ff")
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                mUsers.clear()
                for (snapshot in dataSnapshot.children) {
                    val user = snapshot.getValue(User::class.java)!!
                    assert(fuser != null)
                    if (!user.id.equals(fuser?.uid)) {
                        mUsers.add(user)
                    }
                }
                notifyPropertyChanged(BR.usersList)
            }
        })
    }
}