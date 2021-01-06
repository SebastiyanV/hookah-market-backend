package amvisible.hookahmarket.service.service.impl;

import amvisible.hookahmarket.data.model.Role;
import amvisible.hookahmarket.data.repository.RoleRepository;
import amvisible.hookahmarket.service.model.RoleServiceModel;
import amvisible.hookahmarket.service.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static amvisible.hookahmarket.data.enumerate.RoleEnum.ROLE_USER;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Role getUserRole() {
        return this.roleRepository.getRoleByName(ROLE_USER.toString());
    }

    @Override
    public void addNewRole(RoleServiceModel roleServiceModel) {
        Role role = this.modelMapper.map(roleServiceModel, Role.class);
        this.roleRepository.saveAndFlush(role);
    }

    @Override
    public List<Role> getAll() {
        return this.roleRepository.findAll();
    }
}
