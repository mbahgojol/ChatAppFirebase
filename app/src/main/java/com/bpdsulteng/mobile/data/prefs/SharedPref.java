package com.bpdsulteng.mobile.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.bpdsulteng.mobile.AppLoader;

/**
 * Created by knalb on 11/07/18.
 */

public class SharedPref {
    private static final String TAG = "SharedPref";
    public static final String login = "login";
    public static final String userId = "userid";
    public static final String cookies = "cookies";

    private static SharedPreferences getPref() {
        Context context = AppLoader.appContext;
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLogin(String key, Boolean value) {
        getPref().edit()
                .putBoolean(key, value)
                .apply();
    }

    public static Boolean getLogin(String key) {
        return getPref().getBoolean(key, false);
    }

    public static void setUserId(String value) {
        getPref().edit()
                .putString(userId, value)
                .apply();
    }

    public static String getUserId() {
        return getPref().getString(userId, "");
    }


    public static void deleteLogin(String key) {
        getPref().edit()
                .remove(key)
                .apply();
    }

    public static void setCookies(String value) {
        getPref().edit()
                .putString(cookies, value)
                .apply();
    }

    public static String getCookies() {
        return getPref().getString(cookies, "");
    }


    public static void deleteCookies() {
        getPref().edit()
                .remove(cookies)
                .apply();
    }
}
