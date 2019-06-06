package com.mytt.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
@Api(tags = "TestControler",description = "test")
public class TestControler {



    @GetMapping("/query")
    @ApiOperation("查询test")
    public String query(String name) {
       return "aaaaaaaaaaaaaa";
    }


}
