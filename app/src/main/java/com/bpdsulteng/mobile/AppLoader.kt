package com.bpdsulteng.mobile

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.util.Log

import com.bpdsulteng.mobile.data.realm.RealmManager
import com.bpdsulteng.mobile.di.component.DaggerAppComponent
import com.bpdsulteng.mobile.utils.AppConstants

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
class AppLoader : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    internal lateinit var mCalligraphyConfig: CalligraphyConfig

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        Realm.init(this)
        RealmManager.initializeRealmConfig()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        CalligraphyConfig.initDefault(mCalligraphyConfig)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    companion object {
        lateinit var appContext: Context
        fun log(c: String? = "", m: String) {
            Log.d(AppConstants.TAG, "$c==>$m")
        }
    }
}
