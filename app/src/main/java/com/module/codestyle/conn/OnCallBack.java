package com.module.codestyle.conn;

/**
 * Created by knalb on 23/07/18.
 */

public interface OnCallBack {
    void succses(String response);

    void error(String errorBody, ResponseError responseError);

    default void error() {

    }
}
