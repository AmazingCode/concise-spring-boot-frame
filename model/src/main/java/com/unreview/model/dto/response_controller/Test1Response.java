package com.unreview.model.dto.response_controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class Test1Response  implements Serializable {
    private String recommendUrl;
    private Integer id;

    public Test1Response()
    {}
}