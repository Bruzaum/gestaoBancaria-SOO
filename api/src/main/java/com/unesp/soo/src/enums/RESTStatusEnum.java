package com.unesp.soo.src.enums;

import java.util.HashMap;
import java.util.Map;

public enum RESTStatusEnum {
    OK(200, "OK"),
    CREATED(201, "CREATED"),

    BAD_REQUEST(400, "BAD REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404, "NOT_FOUND"),

    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR") ;

    public final Integer code;
    public final String name;
    private static final Map<Integer, RESTStatusEnum> MAP = new HashMap<>();

    RESTStatusEnum(Integer Status, String name) {
        this.code = Status;
        this.name = name;
    }

    static {
        for (RESTStatusEnum e : values()) {
            MAP.put(e.code, e);
        }
    }

    public static RESTStatusEnum getByCode(Integer code) {
        return MAP.get(code);
    }

}