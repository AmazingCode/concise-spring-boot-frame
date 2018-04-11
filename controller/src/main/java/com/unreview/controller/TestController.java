package com.unreview.controller;

import com.unreview.dao.com.unreview.dao.UserMapper1;
import com.unreview.model.bo.DataResult;
import com.unreview.model.bo.MessageCode;
import com.unreview.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("v1/test")
public class TestController {

    @Autowired
    private ITestService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public DataResult<String> getData(@PathVariable Integer id)
    {
        String result= service.get(id);
        return DataResult.ok(result);
    }
}
