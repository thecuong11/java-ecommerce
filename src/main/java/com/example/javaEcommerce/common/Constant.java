package com.example.javaEcommerce.common;

import java.util.concurrent.TimeUnit;

public class Constant {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";


    public static final String SESSION_ID_REQUEST = "sessionId";
    public static final String JTI_JWT = "jti";
    public static final String SUB_JWT = "sub";
    public static final String PAYLOAD_USER_ID = "userId";
    public static final String PAYLOAD_EMAIL = "email";
    public static final long TOKEN_EXPIRE_TIME = TimeUnit.MINUTES.toMillis(5);
    public static final long REFRESH_TOKEN_EXPIRE_TIME = TimeUnit.MINUTES.toMillis(60);
}
