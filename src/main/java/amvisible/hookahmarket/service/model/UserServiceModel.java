package amvisible.hookahmarket.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel extends BaseServiceModel {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<RoleServiceModel> roles;
}
