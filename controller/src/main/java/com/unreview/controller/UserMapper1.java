package com.unreview.controller;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper1 {

    int delUserInfo(int id);
}
