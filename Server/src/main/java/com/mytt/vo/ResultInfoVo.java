package com.mytt.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

/**
 * Created by Administrator on 2018/7/2.
 */
@ApiModel()
public class ResultInfoVo<T> {

    public static final String SUCCESS="success";
    public static final String FAILED="failed";

    @ApiModelProperty(value = "httpCode")
    private Integer httpCode=200;//httpCode 成功为200
    @ApiModelProperty(value = "业务code：成功为success,失败为其它业务,如roleIdIsNull")
    private String code="success";//业务code 成功为 success  失败为 其它业务编号，如paramIsNull
    @ApiModelProperty(value = "描述信息")
    private String message="处理成功";//描述信息
    @ApiModelProperty(value = "业务数据")
    public T data;//页数据

    public ResultInfoVo(){
        this.httpCode= HttpStatus.OK.value();
        this.code=SUCCESS;
    }

    public ResultInfoVo(T data) {
        this.httpCode= HttpStatus.OK.value();
        this.code=SUCCESS;
        this.data = data;
    }

    public ResultInfoVo(Integer httpCode, String code, String message) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
    }

    public ResultInfoVo(Integer httpCode, String code, String message, T data) {
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
