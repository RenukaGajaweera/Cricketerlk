package com.cricketerlk.common;

/**
 * Created by Supun on 5/21/2017.
 */
public class ClkResponseMessage<T> {

    private T res;
    private String message;

    public ClkResponseMessage() {}

    public ClkResponseMessage(T t, String message) {
        this.res = t;
        this.message = message;
    }

    public T getResObject() {
        return res;
    }

    public void setResObject(T resObject) {
        this.res = resObject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
