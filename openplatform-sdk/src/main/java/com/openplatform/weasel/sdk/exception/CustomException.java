package com.openplatform.weasel.sdk.exception;

/**
 * Created by Administrator on 2017/6/29.
 */
public class CustomException extends Exception {
    private String  error;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String error) {
        super(message);
        this.error = error;
    }
}
