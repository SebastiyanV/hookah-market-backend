package amvisible.hookahmarket.data.repository;

import amvisible.hookahmarket.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role getRoleByName(String name);
}
