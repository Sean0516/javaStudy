package com.exception;

/**
 * Created by Sean on 2018/5/9.
 */
public class CustomException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
//    两个构造器  一个包含错误信息和代码
    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }
//只包含错误信息
    public CustomException(String message) {
        super(message);
    }
}
