package com.unreview.model.dto.controller.response;

import com.unreview.model.enums.ResponseCodeEnum;
import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private String msg;
    private T data;

    public static Response Success() {
        Response result = new Response();
        result.setCode(ResponseCodeEnum.success.getCode());
        result.setMsg(ResponseCodeEnum.success.getDesc());
        return result;
    }

    public static <T> Response<T> Success(T data) {
        Response<T> result = new Response<>();
        result.setCode(ResponseCodeEnum.success.getCode());
        result.setMsg(ResponseCodeEnum.success.getDesc());
        result.setData(data);
        return result;
    }

    public static Response Fail() {
        Response result = new Response();
        result.setCode(ResponseCodeEnum.fail.getCode());
        result.setMsg(ResponseCodeEnum.fail.getDesc());
        return result;
    }

    public static Response Fail(ResponseCodeEnum codeEnum) {
        if (codeEnum == null || codeEnum.equals(ResponseCodeEnum.success)) {
            throw new IllegalArgumentException();
        }
        Response result = new Response();
        result.setCode(codeEnum.getCode());
        result.setMsg(codeEnum.getDesc());
        return result;
    }
}
