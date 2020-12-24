package amvisible.hookahmarket.web.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterServiceModel {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
