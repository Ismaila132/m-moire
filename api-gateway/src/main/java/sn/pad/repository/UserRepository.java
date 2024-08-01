package sn.pad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.pad.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
