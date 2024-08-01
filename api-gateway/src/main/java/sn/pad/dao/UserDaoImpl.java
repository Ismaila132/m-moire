package sn.pad.dao;

import org.springframework.stereotype.Repository;
import sn.pad.entites.Role;
import sn.pad.entites.User;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    private Map<String, User> users = new HashMap<>();
    private Map<String, Role> roles = new HashMap<>();

    @Override
    public void saveRole(Role role) {
        roles.put(role.getRolename(), role);
    }

    @Override
    public void saveUser(User user, String roleName) {
        Role role = roles.get(roleName);
        if (role != null) {
            user.getRoles().add(role);
            users.put(user.getUsername(), user);
        }
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }
}
