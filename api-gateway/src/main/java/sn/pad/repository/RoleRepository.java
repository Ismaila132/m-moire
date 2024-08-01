package sn.pad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.pad.entites.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
}
