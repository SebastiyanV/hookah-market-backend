package amvisible.hookahmarket.configuration.initial_data;

import amvisible.hookahmarket.data.enumerate.RoleEnum;
import amvisible.hookahmarket.data.model.Role;
import amvisible.hookahmarket.service.model.RoleServiceModel;
import amvisible.hookahmarket.service.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleInitialData {

    private final RoleService roleService;

    @Autowired
    public RoleInitialData(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        List<String> roles = this.roleService.getAll()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toList());

        Arrays.stream(RoleEnum.values())
                .forEach(role -> {
                    if (!roles.contains(role.name())) {
                        this.roleService.addNewRole(new RoleServiceModel(role.toString()));
                    }
                });
    }
}
