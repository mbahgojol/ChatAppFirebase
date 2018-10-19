package com.module.codestyle.conn;


import java.io.File;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by knalb on 19/07/18.
 */

public class ApiManager {
    private ApiService apiService;

    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public void doDelete(String token, String url, ApiObserver apiObserver) {
        apiService.doDelete(token, url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver);
    }

    public void doPost(String url, RequestBody postData, ApiObserver apiObserver) {
        apiService.doPost(url, postData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver);
    }

    public void doPost(String token, String url, RequestBody postData, ApiObserver apiObserver) {
        apiService.doPost(token, url, postData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver);
    }

    public void doGet(String url, ApiObserver apiObserverAny) {
        apiService.doGet(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserverAny);
    }

    public void doGetToken(String token, String url, ApiObserver apiObserverAny) {
        apiService.doGetToken(token, url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserverAny);
    }

    public void doTest(String token, String url, ApiObserver observer) {
        apiService.doTest(token, url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void doGet(String url, String query, ApiObserver apiObserverAny) {
        apiService.doGet(url, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserverAny);
    }

    public void doGet(String url, Map<String, String> query, ApiObserver apiObserverAny) {
        apiService.doGet(url, query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserverAny);
    }

    public void doUploadImage(String token, String path, File file, ApiObserver apiObserver, ProgressRequestBody.ProgressCallback listener) {
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), "upload Image");
        ProgressRequestBody requestFile = new ProgressRequestBody(MediaType.parse("multipart/form-data"), file, listener);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile); // name is key from server
        apiService.doUploadFile(token, path, description, body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver);
    }

    public void doUploadVideo(String token, String path, File file,
                              ApiObserver apiObserver, ProgressRequestBody.ProgressCallback listener) {
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), "upload Video");
        RequestBody videoFile = RequestBody.create(
                MediaType.parse("multipart/form-data"),
                file
        );
        ProgressRequestBody requestFile = new ProgressRequestBody(MediaType.parse("multipart/form-data"), file, listener);
        MultipartBody.Part body = MultipartBody.Part.createFormData("fileToUpload", file.getName(), requestFile); // name is key from server
        apiService.doUploadFile(token, path, description, body)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(apiObserver);
    }
}
