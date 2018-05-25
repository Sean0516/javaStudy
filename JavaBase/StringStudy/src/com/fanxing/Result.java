package com.fanxing;

/**
 * Created by Sean on 2018/5/21.
 */
public class Result<T> {
    private T data;
    private String code;
    public Result(T data,String code){
        this.data=data;
        this.code=code;
    }
    public Result(){};

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
