package com.unreview.model.bo;

import lombok.Data;

public enum  MessageCode
{
    ok(0,"成功"),
    generalError(500,"一般性错误");
    private int code;
    private String message;

    MessageCode(int code,String message)
    {
        this.code=code;
        this.message=message;
    }
    public int code()
    {
        return code;
    }
    public String message()
    {
        return message;
    }
}
