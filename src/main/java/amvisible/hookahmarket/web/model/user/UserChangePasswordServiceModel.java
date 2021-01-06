package amvisible.hookahmarket.web.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangePasswordServiceModel {

    private String currentPassword;
    private String newPassword;
}
