package com.news.model;

/**
 * Created by root on 16-10-14.
 */

public class HttpRequset<T> {
    private String reason;
    private T result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
