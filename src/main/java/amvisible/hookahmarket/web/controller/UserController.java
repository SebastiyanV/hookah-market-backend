package amvisible.hookahmarket.web.controller;

import amvisible.hookahmarket.exceptions.InvalidPasswordException;
import amvisible.hookahmarket.service.service.UserService;
import amvisible.hookahmarket.web.model.MessageResponseModel;
import amvisible.hookahmarket.web.model.user.UserChangePasswordServiceModel;
import amvisible.hookahmarket.web.model.user.UserEditProfileServiceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static amvisible.hookahmarket.data.constant.Message.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/settings/edit-profile", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> editProfile(
            @RequestBody UserEditProfileServiceModel userEditProfileServiceModel,
            Principal principal) {

        try {
            this.userService.editProfile(userEditProfileServiceModel, principal.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(THERE_IS_A_PROBLEM));
        }

        return ResponseEntity.ok().body(new MessageResponseModel(PROFILE_UPDATED));
    }

    @PostMapping(value = "/settings/change-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changePassword(
            @RequestBody UserChangePasswordServiceModel serviceModel,
            Principal principal
    ) {
        try {
            this.userService.changePassword(serviceModel, principal.getName());
        } catch (InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(INVALID_PASSWORD));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponseModel(THERE_IS_A_PROBLEM));
        }
        return ResponseEntity.ok().body(new MessageResponseModel(PROFILE_UPDATED));

    }
}
