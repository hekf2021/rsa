package com.mytt.rsacode.rsa.model;


public class ResultInfo<T> {

    public static final String SUCCESS="success";
    public static final String FAILED="failed";


    private Integer httpCode=200;//httpCode 成功为200

    private String code="success";//业务code 成功为 success  失败为 其它业务编号，如paramIsNull

    private String message="处理成功";//描述信息

    public T data;//页数据

    public ResultInfo(){
        this.httpCode= 200;
        this.code=SUCCESS;
    }

    public ResultInfo(T data) {
        this.httpCode= 200;
        this.code=SUCCESS;
        this.data = data;
    }

    public ResultInfo(Integer httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public ResultInfo(Integer httpCode, String code, String message, T data) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
