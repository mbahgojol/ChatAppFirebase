package com.bpdsulteng.mobile.conn;

public class ResponseError {
    private String protocol;
    private int code;
    private String message;
    private String url;

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return
                "ResponseError{" +
                        "protocol = '" + protocol + '\'' +
                        ",code = '" + code + '\'' +
                        ",message = '" + message + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}
