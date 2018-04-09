package com.unreview.service.implement;


import com.unreview.dao.com.unreview.dao.dao1;
import com.unreview.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService implements ITestService {
    @Autowired
    private dao1 dao;

    public  Integer get()

    {
        return dao.get().get(0).getQCount();
        //return new daotest().get();
    }
}
