package com.mytt.controller;


import com.mytt.componet.RSARequest;
import com.mytt.rsacode.rsa.model.ResultInfo;
import com.mytt.rsacode.rsa.utils.FastJsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/test/")
@Api(tags = "TestControler",description = "test")
public class TestControler {

    @GetMapping("/pay")
    @ApiOperation("请求支付")
    public ResultInfo query() throws Exception {
        Map<String,Object> businessMap = new TreeMap<>();
        businessMap.put("orderId", "123234436463");
        businessMap.put("amount", 5000);
        String url ="http://localhost:8081/server/api/pay";
        String str = RSARequest.send(FastJsonUtils.mapToJson(businessMap),url);
        System.out.println("请求结果="+str);
       return new ResultInfo(str);
    }


}
