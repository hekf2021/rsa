package com.mytt.controller;


import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.model.ResultInfo;
import com.mytt.rsacode.rsa.model.RsaRuquest;

import com.mytt.rsacode.rsa.utils.AESUtils;
import com.mytt.rsacode.rsa.utils.RSARequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@Api(tags = "PayControler",description = "支付接口")
public class PayControler {

    public static final String serverPrivateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCFRrQUtOfOSPneJaNDRyZyVoyd4mui2wHBkE8t/+r5FQzzThROtwnoSiuuMEQqWT53UVVWF3OYcuCOa5NjvfB5xbb9sBxr4ibG8Wis0RK0JFwpcAEthnRIhdYe6H4RY7DZ2/waPu5O9Gqj6ALbTFyJUyOuE/+CperVzOV7ChNbtbmWnu+tmMQcj74GUTOUotaEHeswW9m+YDLsw3lTDmogQ6PXGSXBW6zWweoQfHqxl40BG1UHq+MzTA21potrxhug+d9Z/zQ0mhpPbfQU7k0LyeMvhjjOd/2ngH24QCWyHIOvn+GBtdjVtlP+5K7uwrMlBBuj8L01Td4iakNNvb15AgMBAAECggEASCThzmOvJ4dxWsNdAUo+kZ4wODcctOeAp9sKQYhLFdJDVGFrtMB4aHnd80jFXXc5zTUF0LiZJmjpDa9F9bQXvJM+X0051VxySTbcxs/WJBWangO3X4Fs6OUp50lLFPa2APiIG1z9e1frchZhlXxciymqIs1Iftx5popnnpbxq66Gu55ISQQi+ULaJFqFZV3aWGt0XPL3gkg6xRS+raixAUcYm1SK8MJNAz4ePRQpIWtN7zPwIqjqlPDDy8pry4/e+9XtrrP3dcbC0KV3o8LS8c0bOj1P669tQutLc5qzeTdZtH+pqvetK4WNrgG33vgper8hW8Oqh5wfNikTY4dkAQKBgQDI9sb2PJ2tUFImlifPg9cyQqeemYRUCALkIC+QsxTpIOZqxp0MONRi2TgN3zI4yK/z0DM2PFrJGDQoBlZSKAWnqZH4WgKMZKpaH6l/MsGxN7BFJqjpPhU+EG3wTXHFq5yFMitV4ImjsWThORjvXmfBStyBVIIanEyBJ5sMxZ/o4QKBgQCpxnUG+9/IgckglFpj7A5oUQHTLo8ZBzdQfiCYrngdFT4mqP83FM0oS1W4UdT+WvINUvQTM2UYns6iMgZrgOsRTzSR9dfz+SsBZV3tcSbQ9rVVlthyKlqo0/GhMoiWUf1GMNa6I+tn1Xglpk8Y89nqs523RPnNugHcxo/9B3JvmQKBgQCZpvUgt9/m746gKhZuN2OF7iiyvrXKegWb3m//tqy6QolqAkl5WsooeeS91nVTrT26sOk3ApZ3QqY+I/pD/o20T5ZnyIpCRYVqOuMhefFWSjnf5VsoWmtrdKxaGxxuAnQ98snnczJJkgyLl1hVTF0QEOjfiXmgVvi3l0yZS1CkgQKBgQCKZhbmf5N5D5wW6/kXtco07byQHEdY/tHv0zHdZXTTOIr21xNeIp3v+CHD5Sm9xAPIKtR/hokmXedSv3cOUgTOkoPnbMMDxx5wv+Km+nPLKyK9x/9gafiH4mb6oohICkRu4Mda7IctmQ6dmoqma7s8ofmy+g6tz7FO+atNeLGoGQKBgHO9XVh6egLv2cOtBDUNU/Y78iAba/MMUCTcDigaZLbZD1/cQ/w6ZJ5SjwmP1DaEqZ7W2wiktTTiepEqgWnKRgi3eXyKShWcGzANb7CTbuaJ8I926VyA1FRGWPBo+ltMwJo+XPkezpZqEPR5A6ngBtedQ5X+5Hz7JxC78CbtG0gA";
    public static final String clientPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlpMToBJRPCwb9uKl8rfEFHCG5hwgJKbqdnQDnGeAnmSpKZ0fiwSF9GKl6sB8LyU+BKfeXfLQFZaVOSr7lwa+jGoMtRmapvEipCNocEnPsKYNIpEuiH5kqcYPdrVRuq4mZak0eN9Y97AWN8OD+alqAI1pQuwXGX2MVxyP3BvNNaXJTO8JoEiLiQ0a38m0cxxE/hsgm8754JOYHsyPImMLOSRC0f9B+sDdulXFjhGb6D4bNDs0UFNu9yWzgZdpHqHEbvcqvDEt1ceNEtVtbxOCYsLvFJkWNIdAwlIYKxVlVpr3/kwdcaOsU44+vVKyjkPl/3r3FiKgE8K83wrAPfkRVQIDAQAB";

    @PostMapping("/pay")
    @ApiOperation("查询test")
    public ResultInfo pay(@RequestBody RsaRuquest rsaRuquest) throws Exception {
        System.out.println("rsaRuquest="+rsaRuquest);
        try {
            RSARequestUtils.verifyBody(rsaRuquest, clientPublicKey, serverPrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("业务处理......");
        //生成返回参数
        Map<String,Object> businessMap = new HashMap<>();
        businessMap.put("serverOrderId", AESUtils.randomKey());
        businessMap.put("state", 1);
        businessMap.put("create", "2019-02-23 12:23:45");
        JSONObject jsonObject = new JSONObject(businessMap);
        Map<String, Object> resultMap = RSARequestUtils.buildBody(jsonObject.toJSONString(), clientPublicKey, serverPrivateKey, businessMap);
        return new ResultInfo(resultMap);
    }


}
