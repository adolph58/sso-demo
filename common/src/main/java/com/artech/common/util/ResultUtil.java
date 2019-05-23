package com.artech.common.util;


import com.artech.common.enumeration.ReturnCode;

/**
 * Created by 程江涛 on 2018/3/13 0013
 */
public class ResultUtil {
    public static Result success(Object data) {
        return new Result(ReturnCode.success.getCode(), ReturnCode.success.getMsg(), data);
    }

    public static Result success() {
        return success(null);
    }

    public static Result fail() {
        return new Result(ReturnCode.fail.getCode(), ReturnCode.fail.getMsg(), null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result serverError() {
        return error(ReturnCode.serverError.getCode(), ReturnCode.serverError.getMsg());
    }
}
