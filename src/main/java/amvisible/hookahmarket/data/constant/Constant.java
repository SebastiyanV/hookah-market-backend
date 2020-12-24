package amvisible.hookahmarket.data.constant;

public class Constant {

    // General
    public static final String API_URL = "/api";

    // Urls
    public static final String AUTH_URL = API_URL + "/auth";
    public static final String TEST_URL = API_URL + "/test"; // TODO: don't forget to delete this

    // Security
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String AUTHORIZATION_TOKEN_TYPE = "Bearer ";
    public static final String JWT_SECRET_KEY = "secret";
    public static final int JWT_EXPIRATION_TIME = 1800000; // in millis
}
