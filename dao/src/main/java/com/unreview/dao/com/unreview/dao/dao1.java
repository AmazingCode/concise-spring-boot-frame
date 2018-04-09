package com.unreview.dao.com.unreview.dao;

import com.unreview.model.po.Category;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface dao1 {
   List<Category> get();
}
