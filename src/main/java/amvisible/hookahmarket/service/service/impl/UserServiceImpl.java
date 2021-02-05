package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.data.repository.UserRepository;
import amvisible.hookahmarket.exceptions.InvalidPasswordException;
import amvisible.hookahmarket.service.model.UserServiceModel;
import amvisible.hookahmarket.service.service.RoleService;
import amvisible.hookahmarket.service.service.UserService;
import amvisible.hookahmarket.web.model.user.UserChangePasswordServiceModel;
import amvisible.hookahmarket.web.model.user.UserEditProfileServiceModel;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

import static amvisible.hookahmarket.data.constant.Message.INVALID_PASSWORD;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AmazonService amazonService;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            RoleService roleService,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            AmazonService amazonService) {

        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.amazonService = amazonService;
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
    public Optional<User> getUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setRoles(List.of(this.roleService.getUserRole()));
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void editProfile(UserEditProfileServiceModel userEditProfileServiceModel, String email) {
        User user = this.userRepository.findUserByEmail(email).orElse(null);
        if (null != user) {
            user.setFirstName(userEditProfileServiceModel.getFirstName());
            user.setLastName(userEditProfileServiceModel.getLastName());
            user.setPhoneNumber(userEditProfileServiceModel.getPhoneNumber());
            this.userRepository.saveAndFlush(user);
        }
    }

    @SneakyThrows
    @Override
    public void changePassword(UserChangePasswordServiceModel serviceModel, String email) {
        User user = this.userRepository.findUserByEmail(email).orElse(null);
        if (null != user) {
            if (!this.bCryptPasswordEncoder.matches(serviceModel.getCurrentPassword(), user.getPassword())) {
                throw new InvalidPasswordException(INVALID_PASSWORD);
            }
            user.setPassword(this.bCryptPasswordEncoder.encode(serviceModel.getNewPassword()));
            this.userRepository.saveAndFlush(user);
        }
    }

    @Override
    public void updateProfilePicture(MultipartFile profilePicture, String email) {
        User user = this.userRepository.findUserByEmail(email).orElse(null);
        if (null != user) {
            String fileExtension = FilenameUtils.getExtension(profilePicture.getOriginalFilename());
            String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
            user.setProfilePicture(newFileName);
            this.userRepository.saveAndFlush(user);
            this.amazonService.uploadFile(profilePicture, newFileName);
        }
    }

    @Override
    public void deleteProfilePicture(String email) {
        User user = this.userRepository.findUserByEmail(email).orElse(null);
        if (null != user) {
            user.setProfilePicture(null);
//            this.amazonService.deleteFileFromS3Bucket(user.getProfilePicture());
            this.userRepository.saveAndFlush(user);
            System.out.println(1);
        }
    }

    @Override
    public Map<String, String> getProfilePictureNameByEmail(String name) {
        Map<String, String> data = new HashMap<>();
        data.put("profilePicture",
                Objects.requireNonNull(
                        this.userRepository.findUserByEmail(name).orElse(null)
                ).getProfilePicture());
        return data;
    }
}
