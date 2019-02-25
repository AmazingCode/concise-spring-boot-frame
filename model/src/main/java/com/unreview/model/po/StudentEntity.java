package com.unreview.model.po;

import lombok.Data;

import java.io.Serializable;
@Data
public class StudentEntity implements Serializable {
    private Integer id;
    private Integer uid;
    private String name;
    private String description;
}
