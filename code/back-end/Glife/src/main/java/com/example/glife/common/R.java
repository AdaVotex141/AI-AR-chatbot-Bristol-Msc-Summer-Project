package com.example.glife.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Common result class
 */
public class R<T> {
    private Integer code;
    private String msg;
    private T data;
    private Map<String, Object> map = new HashMap<>();

    private R() {
    }

    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.code = 1;
        r.data = data;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.code = 0;
        r.msg = msg;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
