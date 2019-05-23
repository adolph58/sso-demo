package com.artech.common.enumeration;

/**
 * Created by 程江涛 on 2018/12/11.
 */
public enum ReturnCode {
    success(200, "操作成功"),
    empty(201, "资源为空"),
    sessionFail(202, "session失效"),
    loginFail(203, "登录失败"),
    fail(400, "操作失败"),
    serverError(500, "服务端错误");

    private Integer code;
    private String msg;
    ReturnCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
