package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.exceptions.InvalidPasswordException;
import amvisible.hookahmarket.service.model.UserServiceModel;
import amvisible.hookahmarket.web.model.user.UserChangePasswordServiceModel;
import amvisible.hookahmarket.web.model.user.UserEditProfileServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean userExistsByEmail(String email);

    Optional<User> getUserByEmail(String email);

    void register(UserServiceModel userServiceModel);

    void editProfile(UserEditProfileServiceModel userEditProfileServiceModel, String email);

    void changePassword(UserChangePasswordServiceModel serviceModel, String name) throws InvalidPasswordException;

    void updateProfilePicture(MultipartFile profilePicture, String email);

    void deleteProfilePicture(String email);

    Map<String, String> getProfilePictureNameByEmail(String name);
}
