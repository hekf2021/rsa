package com.mytt.rsacode.rsa.utils;

import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.constants.Constants;

import java.util.TreeMap;

public class RSARequestUtils {

    /**
     * 对请求参数进行加密和签名
     * @param json   http request parameters
     * @return 加密和签名后的json字符串
     */
    private String buildRequestStr( String json,String publicKey,String privateKey) throws Exception {
        // 生成AES 随机密钥
        String randomKey = AESUtils.randomKey();
        // 对AES密钥加密
        String randomKeyEncrypt = RSAUtils.encryptByPublicKey(randomKey,publicKey);
        // 用AES密钥对请求参数加密
        String data = AESUtils.encrypt(json, randomKey);
        TreeMap<String, Object> tempMap = new TreeMap<>();
        tempMap.put(Constants.DATA, data);
        tempMap.put(Constants.RANDOM_KEY_ENCRYPT, randomKeyEncrypt);
        tempMap.put(Constants.DATA, System.currentTimeMillis());
        JSONObject jsonObject = new JSONObject(tempMap);
        String signStr = jsonObject.toJSONString();
        // 对加密后的输入参数签名
        String signedStr = RSAUtils.sign(signStr, privateKey);
        jsonObject.put(Constants.SIGN_DATA, signedStr);
        return jsonObject.toJSONString();
    }
}
