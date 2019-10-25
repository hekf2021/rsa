package com.mytt.rsacode.rsa.utils;

import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.constants.Constants;
import com.mytt.rsacode.rsa.model.RsaRuquest;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RSARequestUtils {

    /**
     * 对请求参数进行加密和签名
     * @param bizContent   请求内容
     * @return 加密和签名后的json字符串
     */
    public static Map<String,Object> buildBody(String bizContent,String publicKey,String privateKey,Map<String,Object> publicParamMap) throws Exception {
        // 生成AES 随机密钥
        String randomKey = AESUtils.randomKey()+"abadfasdfa";
        // 对AES密钥加密
        String randomKeyEncrypt = RSAUtils.encryptByPublicKey(randomKey,publicKey);
        Map<String,Object> map = new TreeMap<>();
        map.put(Constants.RSA_REQUEST.BIZ_CONTENT,bizContent);
        map.put(Constants.RSA_REQUEST.RANDOM_KEY,randomKey);
        map.put(Constants.RSA_REQUEST.TIMESTAMP,Long.valueOf(System.currentTimeMillis()).toString());
        if(publicParamMap!=null){
            map.putAll(publicParamMap);
        }
        String json = new JSONObject(map).toJSONString();
        System.out.println("生成签名map "+json);
        String sign = RSAUtils.sign(json, privateKey);
        map.remove(Constants.RSA_REQUEST.RANDOM_KEY);
        map.put(Constants.RSA_REQUEST.RANDOM_KEY_ENCRYPT,randomKeyEncrypt);
        map.put(Constants.RSA_REQUEST.SIGN,sign);
        return map;
    }


    /**
     * 验签
     * @param rsaRuquest
     * @param publicKey
     * @param privateKey
     * @throws Exception
     */
    public static void verifyBody(RsaRuquest rsaRuquest, String publicKey, String privateKey) throws Exception{
        //验签
        String randomKey = RSAUtils.decryptByPrivateKey(rsaRuquest.getRandomKeyEncrypt(),privateKey);
        Map<String,Object> map = new TreeMap<>();
        map.put(Constants.RSA_REQUEST.APP_ID,rsaRuquest.getAppId());
        map.put(Constants.RSA_REQUEST.BIZ_CONTENT,rsaRuquest.getBizContent());
        map.put(Constants.RSA_REQUEST.CHARSET,rsaRuquest.getCharset());
        map.put(Constants.RSA_REQUEST.TIMESTAMP,rsaRuquest.getTimestamp());
        map.put(Constants.RSA_REQUEST.SDK_VERSION,rsaRuquest.getSdkVersion());
        map.put(Constants.RSA_REQUEST.RANDOM_KEY,randomKey);
        String json = new JSONObject(map).toJSONString();
        System.out.println("验签map "+json);
        boolean state =  RSAUtils.verify(json, publicKey, rsaRuquest.getSign());
        if(!state){
            throw new Exception("验签失败");
        }
    }
}
