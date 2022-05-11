package com.niuniu.hcs_discuz.utils;

import com.niuniu.hcs_discuz.constant.ResultCodeCst;

import java.util.HashMap;
import java.util.Map;

/**
 * 响应给前端的数据
 *
 * @author niuniu
 * @date 2022/2/27
 */
public class JsonResult {
    private String jsessionid;
    private String token;
    private boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> datas = new HashMap();

    //无参
    public JsonResult() {
    }

    //有参
    public JsonResult(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    //成功时
    public void setSuccess() {
        this.success = true;
        this.code = ResultCodeCst.SUCCESS_CODE;
        this.message = ResultCodeCst.SUCCESS_STR;
    }

    //成功时
    public void setSuccess(String message) {
        this.success = true;
        this.code = ResultCodeCst.SUCCESS_CODE;
        this.message = message;
    }

    //失败时
    public void setFail() {
        this.success = false;
        this.message = ResultCodeCst.FAIL_STR;
    }

    //失败时
    public void setFail(String err) {
        this.success = false;
        this.message = err;
    }

    //系统错误时
    public void setSysErr() {
        this.code = ResultCodeCst.SYS_ERR_CODE;
        this.setFail(ResultCodeCst.SYS_ERR_STR);
    }

    public void addDatas(String key, Object value) {
        if (!key.isEmpty()) {
            this.datas.put(key, value);
        }
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }
}
