package amvisible.hookahmarket.data.constant;

public class Constant {

    // General
    public static final String API_URL = "/api";

    // Urls
    public static final String AUTH_URL = API_URL + "/auth";
    public static final String GET_ARTICLES_URL = API_URL + "/article/get";

    // Security
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String AUTHORIZATION_TOKEN_TYPE = "Bearer ";
    public static final String JWT_SECRET_KEY = "secret";
    public static final int JWT_EXPIRATION_TIME = 8600000; // 24 hours in milliseconds
}
