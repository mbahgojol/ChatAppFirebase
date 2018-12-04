package com.bpdsulteng.mobile.conn;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by knalb on 19/07/18.
 */

public interface ApiService {
    @DELETE
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doDelete(@Header("Authorization") String token, @Url String url);

    @POST
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doPost(@Url String url, @Body RequestBody params);

    @POST
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doPost(@Header("Authorization") String token, @Url String url, @Body RequestBody params);

    @GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doGetToken(@Header("Authorization") String token, @Url String url);

    @GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doTest(@Header("Authorization") String token, @Url String url);

    @GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doGet(@Url String url);

    @GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doGet(@Url String url, @Query("q") String q);

    @GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doGet(@Url String url, @QueryMap(encoded = false) Map<String, String> options);

    /*@GET
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Observable<String> doGet(@Url String url, @Path(value = "id", encoded = true) String id);*/

    @Multipart
    @POST
    Observable<String> doUploadFile(@Header("Authorization") String token, @Url String path, @Part("description")
            RequestBody description, @Part
                                            MultipartBody.Part file);

    @POST
    Observable<String> doUploadArrayFile(@Header("Authorization") String token, @Url String path, @Body RequestBody photo);
}
