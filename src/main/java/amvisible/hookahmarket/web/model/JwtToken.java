package amvisible.hookahmarket.web.model;

import amvisible.hookahmarket.data.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {

    private static final String TOKEN_TYPE = "Bearer ";

    private String token;
    private String type;
    private User user;

    public JwtToken(String token, User user) {
        this.token = token;
        this.type = TOKEN_TYPE;
        this.user = user;
    }
}
