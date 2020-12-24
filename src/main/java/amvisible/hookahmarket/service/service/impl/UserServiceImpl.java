package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.data.repository.UserRepository;
import amvisible.hookahmarket.service.model.UserServiceModel;
import amvisible.hookahmarket.service.service.RoleService;
import amvisible.hookahmarket.service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findUserByEmail(email)
                .orElse(null);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRoles(List.of(this.roleService.getUserRole()));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.saveAndFlush(user);
    }
}
