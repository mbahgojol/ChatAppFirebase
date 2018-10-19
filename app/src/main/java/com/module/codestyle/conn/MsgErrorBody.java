package com.module.codestyle.conn;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class MsgErrorBody {

    @SerializedName("path")
    private String path;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("status")
    private int status;

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "MsgErrorBody{" +
                        "path = '" + path + '\'' +
                        ",error = '" + error + '\'' +
                        ",message = '" + message + '\'' +
                        ",timestamp = '" + timestamp + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}