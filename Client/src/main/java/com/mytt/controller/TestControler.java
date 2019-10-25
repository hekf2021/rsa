package com.mytt.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.constants.Constants;
import com.mytt.rsacode.rsa.model.ResultInfo;
import com.mytt.rsacode.rsa.utils.HttpUtil;
import com.mytt.rsacode.rsa.utils.RSARequestUtils;
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

    public static final String serverPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhUa0FLTnzkj53iWjQ0cmclaMneJrotsBwZBPLf/q+RUM804UTrcJ6EorrjBEKlk+d1FVVhdzmHLgjmuTY73wecW2/bAca+ImxvForNEStCRcKXABLYZ0SIXWHuh+EWOw2dv8Gj7uTvRqo+gC20xciVMjrhP/gqXq1czlewoTW7W5lp7vrZjEHI++BlEzlKLWhB3rMFvZvmAy7MN5Uw5qIEOj1xklwVus1sHqEHx6sZeNARtVB6vjM0wNtaaLa8YboPnfWf80NJoaT230FO5NC8njL4Y4znf9p4B9uEAlshyDr5/hgbXY1bZT/uSu7sKzJQQbo/C9NU3eImpDTb29eQIDAQAB";
    public static final String clientPrivateKey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCWkxOgElE8LBv24qXyt8QUcIbmHCAkpup2dAOcZ4CeZKkpnR+LBIX0YqXqwHwvJT4Ep95d8tAVlpU5KvuXBr6Magy1GZqm8SKkI2hwSc+wpg0ikS6IfmSpxg92tVG6riZlqTR431j3sBY3w4P5qWoAjWlC7BcZfYxXHI/cG801pclM7wmgSIuJDRrfybRzHET+GyCbzvngk5gezI8iYws5JELR/0H6wN26VcWOEZvoPhs0OzRQU273JbOBl2keocRu9yq8MS3Vx40S1W1vE4Jiwu8UmRY0h0DCUhgrFWVWmvf+TB1xo6xTjj69UrKOQ+X/evcWIqATwrzfCsA9+RFVAgMBAAECggEASnmeiFrnASsT/c3mpKGOA0oQ/vnEUDya1VHWg2KSNEW3gdSsSFdJZjHwvKbOBYeXIDhjfPZfQ35Ceo8u92knkE3X0S9D1fSI3MSCoJ9xcX8AGivS50IUygAjn+RY80u1xCynvm1CFOhsZNJ4kWDhBDrtaGNbnYkChAelhAngOvrt2GAJpuMJDzWG1l9Kc0Qov3xZW9wqwj89452/G14Mvi6cRE+gaG/yQUCYrjX6fBrmwZeuJEf1ChAVwv3YauMDDsG2gugjWPgMgtw3BNEFtr59sIDzym6RzcbVD4nQmCCk5a7YYZ0tVkk2ivRL/ZYT327qkALP7TqsvH1LjdllgQKBgQDFOkKOTY9lp49ot7rYt4KRpZSLIfWCe6Ke9PsAI+vYFXZC2D6edezxP/pRp2EMNNaY1XQX3iNJy2alfnyVYb6hSMw8zs6m/UExE/CMuy54yYifIMWYRtUqW7pcRlHzL+EqSsAUvPrCMlqsyQrrs012m0cK0YYRD0uUmfP6Znt1tQKBgQDDcdUlafPV1kGKKffesKRCb9q51bO6FAPKyjagRePcloYCy6/PrHiLFV067/rdOQc/LHmC+QPGKbEj/ZBU+dZeCwyZwAAQzMMgn1XknqJSjOHbI7A0BRnqRC+S9yZmyYA63/kRRVzcfKaruQKkTelOQ9MNH10WGOhUcpg0tPJxIQKBgQCjAfH8V1JLfF/k9uWxNIrwAeBx1hFqVPElW+kQRqAoqiRiPwQSR/kbs/sZ/bMSOaGcE9+t0mVsGnauFYq2lGDQQLC9GVhnYC2/eHF6f0J7hmYqzxVBNFn+dm3i0iPtUQdSwxFako7WbnPPFyK1iC5f9xqScHXUJ8YZ/dxOeuFTeQKBgFu5P6QYboQL0qRp/9c2JVC8t/UBEyEgJYt1jV94jXDH/8quFyuqsMPtWdzAJYbPLdR7JefOv/OvO2A9rxACVJW8L8S0dBNuVaEoxiHORTXiwlibDiXLwxbLu1wIu6//nzuCQ5zUZblgksQCxvefTQAbGqCM+J3XQKnpQf9ycFmhAoGBAMEqriVAp3KaGlFFz/M4p1Qgigv9RsZGvPLn84H7ps3GSC8feMZSKeOO0yFSMSso1dhX7nGk+qqjGTtRqdx81hNaR/Bf5VAmsZG3N4WbJdhbyzF8pcVisS4jyaZvXDf4Q9cN3bq+Ve6DlmWRqnZOM2yJA8qXbekV87C4XYrMtQlR";


    @GetMapping("/pay")
    @ApiOperation("请求支付")
    public ResultInfo query() throws Exception {
        Map<String,Object> publicParamMap = new TreeMap<>();
        publicParamMap.put(Constants.RSA_REQUEST.APP_ID, Constants.APP_ID);
        publicParamMap.put(Constants.RSA_REQUEST.CHARSET,Constants.CHARSET);
        publicParamMap.put(Constants.RSA_REQUEST.SDK_VERSION,Constants.SDK_VERSION);

        Map<String,Object> businessMap = new TreeMap<>();
        businessMap.put("orderId", "123234436463");
        businessMap.put("amount", 5000);
        JSONObject jsonObject = new JSONObject(businessMap);

        Map<String, Object> bodyMap = RSARequestUtils.buildBody(jsonObject.toJSONString(), serverPublicKey, clientPrivateKey, publicParamMap);
        System.out.println("请求bodyMap "+bodyMap);
        String url ="http://localhost:8081/server/api/pay";
        JSONObject requestJsonObject = new JSONObject(bodyMap);
        String result = HttpUtil.doPost(url,requestJsonObject.toJSONString());
        System.out.println("http请求返回结果："+result);
        ResultInfo resultInfo = JSON.parseObject(result, ResultInfo.class);
       return resultInfo;
    }


}
