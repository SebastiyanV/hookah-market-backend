package amvisible.hookahmarket.web.controller;

import amvisible.hookahmarket.data.model.User;
import amvisible.hookahmarket.service.model.UserServiceModel;
import amvisible.hookahmarket.service.service.UserService;
import amvisible.hookahmarket.web.model.JwtToken;
import amvisible.hookahmarket.web.model.MessageResponseModel;
import amvisible.hookahmarket.web.model.user.UserLoginServiceModel;
import amvisible.hookahmarket.web.model.user.UserRegisterServiceModel;
import amvisible.hookahmarket.web.security.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static amvisible.hookahmarket.data.constant.Message.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthController(
            UserService userService,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager,
            JwtUtils jwtUtils) {

        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterServiceModel userRegisterServiceModel) {

        if (this.userService.userExistsByEmail(userRegisterServiceModel.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(EMAIL_ALREADY_EXIST));
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterServiceModel, UserServiceModel.class);

        try {
            this.userService.register(userServiceModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel("There is a problem bla bla"));
        }

        return ResponseEntity.ok().body(new MessageResponseModel(REGISTER_SUCCESSFUL));
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginServiceModel userLoginServiceModel) {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginServiceModel.getEmail(),
                        userLoginServiceModel.getPassword()));

        if (!authentication.isAuthenticated()) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(WRONG_EMAIL_OR_PASSWORD));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = this.jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtToken(
                        jwtToken,
                        user.getId(),
                        user.getEmail(),
                        roles,
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber()));
    }
}




















