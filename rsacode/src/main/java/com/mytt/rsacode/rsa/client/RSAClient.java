package com.mytt.rsacode.rsa.client;

import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.AESUtils;
import com.mytt.rsacode.rsa.RSA;
import com.mytt.rsacode.rsa.constants.Constants;
import com.mytt.rsacode.rsa.model.RsaVo;

import java.util.Map;
import java.util.TreeMap;

public class RSAClient {

    /**
     * 对请求参数进行加密和签名
     * @param bizContent   请求内容
     * @return 加密和签名后的json字符串
     */
    public static Map<String,Object> buildBody(String bizContent,String publicKey,String privateKey) throws Exception {
        // 生成AES 随机密钥
        String randomKey = AESUtils.randomKey()+"abadfasdfa";
        // 对AES密钥加密
        String randomKeyEncrypt = RSA.encryptByPublicKey(randomKey,publicKey);
        Map<String,Object> map = new TreeMap<>();
        map.put(Constants.RSA_REQUEST.APP_ID, Constants.APP_ID);
        map.put(Constants.RSA_REQUEST.CHARSET,Constants.CHARSET);
        map.put(Constants.RSA_REQUEST.SDK_VERSION,Constants.SDK_VERSION);
        map.put(Constants.RSA_REQUEST.BIZ_CONTENT,bizContent);
        map.put(Constants.RSA_REQUEST.RANDOM_KEY,randomKey);
        map.put(Constants.RSA_REQUEST.TIMESTAMP,Long.valueOf(System.currentTimeMillis()).toString());

        String json = new JSONObject(map).toJSONString();
        //System.out.println("生成签名map "+json);
        String sign = RSA.sign(json, privateKey);
        map.remove(Constants.RSA_REQUEST.RANDOM_KEY);//记得要移出
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
    public static void verifyBody(RsaVo rsaRuquest, String publicKey, String privateKey) throws Exception{
        //验签
        String randomKey = RSA.decryptByPrivateKey(rsaRuquest.getRandomKeyEncrypt(),privateKey);
        Map<String,Object> map = new TreeMap<>();
        map.put(Constants.RSA_REQUEST.APP_ID,rsaRuquest.getAppId());
        map.put(Constants.RSA_REQUEST.CHARSET,rsaRuquest.getCharset());
        map.put(Constants.RSA_REQUEST.SDK_VERSION,rsaRuquest.getSdkVersion());
        map.put(Constants.RSA_REQUEST.BIZ_CONTENT,rsaRuquest.getBizContent());
        map.put(Constants.RSA_REQUEST.RANDOM_KEY,randomKey);
        map.put(Constants.RSA_REQUEST.TIMESTAMP,rsaRuquest.getTimestamp());
        String json = new JSONObject(map).toJSONString();
        //System.out.println("验签map "+json);
        boolean state =  RSA.verify(json, publicKey, rsaRuquest.getSign());
        if(!state){
            throw new Exception("验签失败");
        }
    }
}
