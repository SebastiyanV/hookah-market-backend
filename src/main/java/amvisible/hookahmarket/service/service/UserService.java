package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.exceptions.InvalidPasswordException;
import amvisible.hookahmarket.service.model.UserServiceModel;
import amvisible.hookahmarket.web.model.user.UserChangePasswordServiceModel;
import amvisible.hookahmarket.web.model.user.UserEditProfileServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean userExistsByEmail(String email);

    Optional<User> getUserByEmail(String email);

    void register(UserServiceModel userServiceModel);

    void editProfile(UserEditProfileServiceModel userEditProfileServiceModel, String email);

    void changePassword(UserChangePasswordServiceModel serviceModel, String name) throws InvalidPasswordException;
}
