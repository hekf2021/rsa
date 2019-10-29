package com.mytt.rsacode.rsa.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class FastJsonUtils<T> {

    public static<T> T jsonToObject(String json,Class<T> clazz){
        if(StringUtils.isNotEmpty(json)) {
            T t = (T) JSON.parseObject(json, clazz);
            return t;
        }else{
            return null;
        }
    }

    public static<T> String objectToJson(T t){
        String s = JSON.toJSONString(t);
        return s;
    }

    public static<T> String mapToJson(Map map){
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toJSONString();
    }



}
