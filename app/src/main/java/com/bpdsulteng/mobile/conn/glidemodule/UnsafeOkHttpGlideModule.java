package com.bpdsulteng.mobile.conn.glidemodule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.module.wakafmaslahah.utils.NetworkUtils;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * Created by knalb on 15/01/19.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
public class UnsafeOkHttpGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        OkHttpClient client = NetworkUtils.getUnsafeOkHttpClient().build();
        glide.register(GlideUrl.class, InputStream.class,
                new OkHttpUrlLoader.Factory(client));
    }
}
