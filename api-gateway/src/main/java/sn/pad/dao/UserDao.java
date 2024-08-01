package sn.pad.dao;

import sn.pad.entites.Role;
import sn.pad.entites.User;

public interface UserDao {
    void saveRole(Role role);
    void saveUser(User user, String roleName);
    User getUser(String username);
}
