package com.unreview.service.implement;


//import com.unreview.dao.com.unreview.dao.dao1;
//import com.unreview.dao.com.unreview.dao.UserMapper1;

import com.unreview.dao.com.unreview.dao.UserMapper1;
import com.unreview.model.po.NewTable;
import com.unreview.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService {
    @Autowired
    private UserMapper1 dao;

    public  String get(Integer id)
    {
        NewTable result=dao.getNew_tableById(id);
        if(result==null)
            return null;
        return  result.getNew_tablecol();
    }
}
