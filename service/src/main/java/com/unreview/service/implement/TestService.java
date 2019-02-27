package com.unreview.service.implement;


import com.unreview.dao.test.StudentDao;
import com.unreview.model.po.StudentEntity;
import com.unreview.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class TestService implements ITestService {

    @Autowired
   private StudentDao studentDao;

    @Autowired
    private RestTemplate restTemplate1;

    public Integer get() {

        List<StudentEntity> sEntities = studentDao.get();

        Object obj = restTemplate1.getForObject("http://baidu.test",Object.class);
        return null;
    }
}
