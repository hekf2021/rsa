package com.mytt.componet;


import com.alibaba.fastjson.JSONObject;
import com.mytt.constants.Constants;
import com.mytt.rsacode.rsa.client.RSAClient;
import com.mytt.rsacode.rsa.model.RsaVo;
import com.mytt.rsacode.rsa.utils.FastJsonUtils;
import com.mytt.rsacode.rsa.utils.HttpUtil;

import java.util.Map;

public class RSARequest {

    public static String send(String requestJson,String url) throws Exception {
        Map<String, Object> bodyMap = RSAClient.buildBody(requestJson, Constants.serverPublicKey, Constants.clientPrivateKey);
        //System.out.println("请求bodyMap "+bodyMap);
        JSONObject requestJsonObject = new JSONObject(bodyMap);
        String result = HttpUtil.doPost(url,requestJsonObject.toJSONString());
        //System.out.println("http请求返回结果："+result);
        RsaVo rsaVo= FastJsonUtils.jsonToObject(result, RsaVo.class);
        RSAClient.verifyBody(rsaVo, Constants.serverPublicKey,Constants.clientPrivateKey);
        return rsaVo.getBizContent();
    }
}
