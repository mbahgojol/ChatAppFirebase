package com.module.codestyle.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by knalb on 14/09/17.
 */

public final class ClassHelper {
    private static ProgressDialog dialog;

    public static void showProgress(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait");
        dialog.setIndeterminate(true);
        dialog.setCancelable(true);                 //todo edited by kabi
        dialog.setCanceledOnTouchOutside(true);     //todo edited by kabi
        dialog.show();
    }

    public static void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public static void toast(Context context, String isi) {
        Toast.makeText(context, "" + isi, Toast.LENGTH_SHORT).show();
    }

    public static void log(String TAG, String isi) {
        Log.e(TAG, isi);
    }

    public static void blankDialog(final Context c, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c)
                .setMessage(s)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ((AppCompatActivity) c).finish();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void blankDialogFragment(final Context c, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c)
                .setMessage(s)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        ClassHelper.backFragment(c);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void blankDialogFragmentDelete(final Context c, String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c)
                .setMessage(s)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void addFragment(Context context, int container, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment)
                .commit();
    }

    public static void addFragment(Context context, int container, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(container, fragment, TAG)
                .disallowAddToBackStack()
                .commit();
    }

    public static void replaceFragment(Context context, int container, Fragment fragment, String TAG, String backStack) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment, TAG)
                .addToBackStack(backStack)
                .commit();
    }

    public static void replaceFragment(Context context, int container, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    public static void replaceFragment(Context context, int container, Fragment fragment, String TAG) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(container, fragment, TAG)
                .commit();
    }

    public static void removeFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    public static void backFragment(Context c) {
        ((AppCompatActivity) c).getSupportFragmentManager().popBackStack();
    }


    public static void backToActivityResult(Fragment fragment, Intent intent) {
        fragment.getTargetFragment().onActivityResult(fragment.getTargetRequestCode(),
                Activity.RESULT_OK, intent);
    }

    public static void backToActivityResult(Context context, Intent intent) {
        ((AppCompatActivity) context).setResult(Activity.RESULT_OK, intent);
        ((AppCompatActivity) context).finish();
    }

    public static void reloadFragment(Context context, Fragment fragment) {
        final FragmentTransaction ft = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();
    }

    public static String getPath(Context context, Uri uri) {
        String[] projection = new String[]{MediaStore.Video.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            String result = cursor.getString(column_index);
            cursor.close();
            return result;

        } else {
            return null;
        }

    }
}
