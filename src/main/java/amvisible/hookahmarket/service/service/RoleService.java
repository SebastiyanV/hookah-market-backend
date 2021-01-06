package amvisible.hookahmarket.service.service;

import amvisible.hookahmarket.data.model.Role;
import amvisible.hookahmarket.service.model.RoleServiceModel;

import java.util.List;

public interface RoleService {

    Role getUserRole();

    void addNewRole(RoleServiceModel roleServiceModel);

    List<Role> getAll();
}
