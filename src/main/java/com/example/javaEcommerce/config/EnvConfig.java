package com.example.javaEcommerce.config;

public class EnvConfig {
    public static final String MONGODB_PASSWORD;
    public static final String MONGODB_USERNAME;
    public static final String MONGODB_HOST;
    public static final int MONGODB_PORT;
    public static final String MONGODB_DATABASE_NAME;
    public static final String JWT_SECRET;

    static {
        MONGODB_PASSWORD = "admin";
        MONGODB_USERNAME = "root";
        MONGODB_HOST = "localhost";
        MONGODB_PORT = 27017;
        MONGODB_DATABASE_NAME = "ecommerce";
        JWT_SECRET = "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";
    }
}
