package amvisible.hookahmarket.web.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtToken {

    private static final String TOKEN_TYPE = "Bearer ";

    private String token;
    private String type;
    private String id;
    private String email;
    private List<String> roles;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public JwtToken(String token, String id, String email, List<String> roles) {
        this.token = token;
        this.type = TOKEN_TYPE;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public JwtToken(
            String token,
            String id,
            String email,
            List<String> roles,
            String firstName,
            String lastName,
            String phoneNumber) {

        this.token = token;
        this.type = TOKEN_TYPE;
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
}
