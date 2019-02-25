package com.unreview.dao.test;

import com.unreview.model.po.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    List<StudentEntity> get();
}
