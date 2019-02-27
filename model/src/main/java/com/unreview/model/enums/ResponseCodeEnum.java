package com.unreview.model.enums;

public enum  ResponseCodeEnum {
    success(0,"通用成功错误码"),
    fail(-1,"通用失败错误码");
    private Integer code;
    private String desc;
    ResponseCodeEnum(Integer code,String desc){
        this.code=code;
        this.desc=desc;
    }
    public Integer getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
