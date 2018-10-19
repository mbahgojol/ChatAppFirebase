package com.module.codestyle.di.component;

import android.app.Application;

import com.module.codestyle.AppLoader;
import com.module.codestyle.conn.ApiModule;
import com.module.codestyle.di.builder.ActivityBuilder;
import com.module.codestyle.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by knalb on 04/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */

/**
 * Created by knalb on 04/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, ApiModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(AppLoader app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
