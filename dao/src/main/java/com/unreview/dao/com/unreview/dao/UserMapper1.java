package com.unreview.dao.com.unreview.dao;

import com.unreview.model.po.NewTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Mapper
@Component
@ComponentScan
public interface UserMapper1 {

    NewTable getNew_tableById(Integer id);
}
