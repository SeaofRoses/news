package com.news.model;

import java.util.ArrayList;

/**
 * Created by root on 16-10-14.
 */

public class Result<T> {
    private String stat;
    private T data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
