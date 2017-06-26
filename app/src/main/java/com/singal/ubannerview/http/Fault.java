package com.singal.ubannerview.http;

/**
 * 异常类 将异常包装 抛给上一层统一处理
 *
 * Created by li on 2017/6/14.
 */

public class Fault extends RuntimeException {
    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
