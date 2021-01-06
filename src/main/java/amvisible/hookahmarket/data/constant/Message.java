package amvisible.hookahmarket.data.constant;

public class Message {

    // General
    public static final String THERE_IS_A_PROBLEM = "Възникна проблем. Моля опитвайте по-късно или се свържете с администрацията";

    // AuthController
    // --- userRegister
    public static final String EMAIL_ALREADY_EXIST = "Този имейл адрес вече съществува";
    public static final String REGISTER_SUCCESSFUL = "Регистрацията е успешна";
    // --- userLogin
    public static final String WRONG_EMAIL_OR_PASSWORD = "Грешен имейл или парола";

    // UserController
    // --- editProfile
    public static final String PROFILE_UPDATED = "Промените бяха записани успешно";
    // --- changePassword
    public static final String INVALID_PASSWORD = "Грешна парола";

    // ArticleController
    // --- createArticle
    public static final String ARTICLE_CREATED = "Обявата беше успешно публикувана";

}
