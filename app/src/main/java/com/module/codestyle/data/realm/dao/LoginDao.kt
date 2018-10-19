package com.module.jbk.realm.dao

/**
 * Created by knalb on 02/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer Expert
 */

// data access object

class LoginDao {
    /*private val TAG = "LoginDao"
    fun addLogin(login: UserLoginObject) {
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            realm.insertOrUpdate(login)
            Log.d(TAG, "Insert Login Session")
        }
    }

    fun deleteLogin(id: String) {
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            val findFirst = realm.where(UserLoginObject::class.java).equalTo("userId", id).findFirst()
            findFirst!!.deleteFromRealm()
            Log.d(TAG, "Delete Cart")
        }
    }

    fun deleteLogin() {
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            realm.deleteAll()
            Log.d(TAG, "Delete All Login Session")
        }
    }


    fun getLoginByUserId(id: Int): UserLoginObject? {
        val realmInstance = RealmManager.getRealm()
        var result: UserLoginObject? = null
        realmInstance.executeTransaction { realm ->
            result = realm.where(UserLoginObject::class.java).equalTo("userId", id).findFirst()

            Log.d(TAG, "Get Login By UserID")
        }
        return result
    }

    fun getLogin(): UserLoginObject? {
        var result: UserLoginObject? = null
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            result = realm.where(UserLoginObject::class.java).findFirst()!!
            Log.d(TAG, "Get Login Data" + result.toString())
        }
        return result
    }

    fun getFinishedCart(): RealmResults<UserLoginObject>? {
        var result: RealmResults<UserLoginObject>? = null
        val realmInstance = RealmManager.getRealm()
        realmInstance.executeTransaction { realm ->
            result = realm.where(UserLoginObject::class.java).equalTo("isFinish", true).findAllSorted("projectCrtdt", Sort.DESCENDING)
            Log.d(TAG, "Get All Finished Project" + result.toString())
        }
        return result
    }*/
}