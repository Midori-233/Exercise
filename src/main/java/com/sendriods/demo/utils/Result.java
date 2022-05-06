package com.sendriods.demo.utils;

import lombok.Builder;
import lombok.Data;

// TODO 转换成 builder 形式
@Builder
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result<Object> success() {
        return Result.builder()
                .code(String.valueOf(0))
                .msg("成功")
                .build();
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .data(data)
                .msg("成功")
                .code("0")
                .build();
    }

    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = Result.<T>builder()
                .data(data)
                .msg(msg)
                .code("0")
                .build();
        return result;
    }

    public static Result success(String msg) {
        Result<Object> result = Result.builder()
                .msg(msg)
                .build();
        return result;
    }

    public static Result error(String code, String msg) {
        Result<Object> result = Result.builder()
                .msg(msg)
                .code(code)
                .build();
        return result;
    }
}
