package com.unreview.model.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
    private Integer categoryId;
    private String cateGoryName;
    private Integer qCount;
    private String description;
}
