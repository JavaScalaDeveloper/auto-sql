package com.hc.crud.teacherlist.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResult<T> implements Serializable {

    public final static int STATUS_SUCCESS = 0;
    public final static int STATUS_ERROR = 1;

    /**
     * 返回结果
     */
    private T data;
    /**
     * 状态码: 0 - 成功; 其他为失败
     */
    private Integer code;
    /**
     * 请求状态
     */
    private Boolean success = true;
    /**
     * 错误信息
     */
    private String message;
    private String msg;

    /**
     * 扩展信息
     */
    private Map<String, String> exactMap = new HashMap<>();


    public ApiResult() {
        this.code = STATUS_SUCCESS;
        this.success = true;
        this.msg = "";
        this.message = "";
    }

    public ApiResult(T data) {
        this();
        this.data = data;
    }

    public ApiResult(T data, Integer code) {
        this(data, code, "");
    }

    public ApiResult(Integer code, String message) {
        this(null, code, message);
    }

    public ApiResult(T data, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.msg = message;
        this.data = data;
        if (code != null && code != 0) {
            this.success = false;
        }
    }


    public static <T> ApiResult<T> ofData(T data) {
        return new ApiResult<>(data);
    }

    public static <T> ApiResult<T> ofMessage(String message) {
        return new ApiResult<>(STATUS_SUCCESS, message);
    }

    public static <T> ApiResult<T> ofError(int code, String message) {
        return new ApiResult<>(null, code, message);
    }

}
