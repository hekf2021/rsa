package com.mytt.controller;


import com.mytt.componet.RSAResponse;
import com.mytt.rsacode.rsa.model.ResultInfo;
import com.mytt.rsacode.rsa.model.RsaVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Api(tags = "PayControler",description = "支付接口")
public class PayControler {


    @PostMapping("/pay")
    @ApiOperation("查询test")
    public Map pay(@RequestBody RsaVo rsaVo) throws Exception {
        System.out.println("rsaRuquest="+rsaVo);
        try {
            RSAResponse.verifyBody(rsaVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("业务处理......");
        //生成返回参数
        Map<String,Object> businessMap = new HashMap<>();
        businessMap.put("serverOrderId", "Oderid1234344");
        businessMap.put("state", 1);
        businessMap.put("create", "2019-02-23 12:23:45");
        ResultInfo resultInfo = new ResultInfo(businessMap);

        Map<String, Object> resultMap = RSAResponse.buildBody(resultInfo);
        return resultMap;
    }


}
