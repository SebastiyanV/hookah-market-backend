package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Role;
import amvisible.hookahmarket.data.repository.RoleRepository;
import amvisible.hookahmarket.service.service.RoleService;
import org.springframework.stereotype.Service;

import static amvisible.hookahmarket.data.enumerate.RoleEnum.ROLE_USER;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        return this.roleRepository.getRoleByName(ROLE_USER.toString());
    }
}
