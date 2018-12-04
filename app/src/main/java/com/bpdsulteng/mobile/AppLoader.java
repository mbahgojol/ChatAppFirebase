package com.bpdsulteng.mobile;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bpdsulteng.mobile.data.realm.RealmManager;
import com.bpdsulteng.mobile.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by knalb on 19/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class AppLoader extends Application implements HasActivityInjector {

    @Inject
    public DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    public CalligraphyConfig mCalligraphyConfig;

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        Realm.init(this);
        RealmManager.initializeRealmConfig();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
