package com.unreview.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/test")
public interface TestServiceController {
    @RequestMapping("get")
    Integer get();
}
