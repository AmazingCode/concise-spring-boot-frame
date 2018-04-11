package com.unreview.model.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一消息格式
 * @param <T>
 */
@Data
public class DataResult<T> implements Serializable {

    //数据
    private int status;

    private String message;

    private T data;

    //对外隐藏构造器
    private DataResult(){}

    //创建一个内部的构造器
    private static  DataResult builder()
    {
        return new DataResult();
    }
    private DataResult status(int status)
    {
        this.status=status;
        return this;
    }
    private DataResult message(String message)
    {
        this.message=message;
        return  this;
    }
    private  DataResult data(T data )
    {
        this.data=data;
        return this;
    }

    /**
     * 正常（不允许用户设置消息码与信息message）
     * @param t
     * @param <T>
     * @return
     */
    public static <T> DataResult ok(T t)
    {
        return DataResult.builder()
                .status(MessageCode.ok.code())
                .message(MessageCode.ok.message())
                .data(t);
    }

    /**
     * 正常（不允许用户设置消息码）
     * @param t
     * @param message
     * @param <T>
     * @return
     */
    public static <T> DataResult ok(T t,String message)
    {
        return DataResult.builder()
                .status(MessageCode.ok.code())
                .message(message)
                .data(t);
    }

    /**
     * 异常
     * @param <T>
     * @return
     */
    public static <T> DataResult fail()
    {
        return DataResult.builder()
                .status(MessageCode.generalError.code())
                .message(MessageCode.generalError.message())
                .data(null);

    }

    /**
     * 异常
     * @param <T>
     * @return
     */
    public static <T> DataResult fail(MessageCode code)
    {
        return DataResult.builder()
                .status(code.code())
                .message(code.message())
                .data(null);
    }

}
