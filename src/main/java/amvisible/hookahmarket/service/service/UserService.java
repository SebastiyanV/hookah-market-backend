package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.service.model.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    boolean userExistsByEmail(String email);

    void register(UserServiceModel userServiceModel);
}
