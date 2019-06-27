package com.mytt.rsacode.rsa.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mytt.rsacode.rsa.constants.Constants;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RSAResultUtils {

    public static String decryptStart(String json, String publicKey, String privateKey) throws Exception{
        JSONObject dataJsonObj = JSON.parseObject(json);
        String signData = dataJsonObj.getString(Constants.SIGN_DATA);
        JSONObject orderedJo = sortJSONObject(dataJsonObj);
        orderedJo = orderedJo.fluentRemove(Constants.SIGN_DATA);
        boolean status = RSAUtils.verify(orderedJo.toJSONString(), publicKey, signData);
        if (!status) {
            throw new Exception("验签失败");
        }
        //解密
        String data = dataJsonObj.getString(Constants.DATA);
        // RSA解密
        String randomKeyEncrypt = dataJsonObj.getString(Constants.RANDOM_KEY_ENCRYPT);
        String randomKey = RSAUtils.decryptByPrivateKey(randomKeyEncrypt, privateKey);
        String busData = AESUtils.decrypt(data, randomKey);
        return busData;
    }

    /**
     * 将JSONObject对象中的元素按照key的自然顺序排序
     * @param jsonObject 要排序的JSONObject对象
     * @return
     */
    public static JSONObject sortJSONObject(JSONObject jsonObject) {
        Map<String, Object> treeMap = new TreeMap<String, Object>();
        Set<String> sendJOKeySet = jsonObject.keySet();
        for (Iterator<String> iterator = sendJOKeySet.iterator(); iterator.hasNext(); ) {
            String sendJOKey = iterator.next();
            treeMap.put(sendJOKey, jsonObject.get(sendJOKey));
        }
        return new JSONObject(treeMap);
    }
}
