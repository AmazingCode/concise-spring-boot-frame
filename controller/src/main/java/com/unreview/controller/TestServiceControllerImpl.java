package com.unreview.controller;

import com.unreview.service.interfaces.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServiceControllerImpl implements TestServiceController {

    @Autowired
    private ITestService testService;

    @Override
    public Integer get() {
        return testService.get();
    }
}
