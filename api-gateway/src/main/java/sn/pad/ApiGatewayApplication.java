package sn.pad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import sn.pad.dao.UserDao;
import sn.pad.entites.Role;
import sn.pad.entites.User;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createRole();
        createUsers();
    }

    public void createRole() {
        Role r1 = new Role();
        r1.setRolename("ROLE_CHEF");

        Role r2 = new Role();
        r2.setRolename("ROLE_ASSISTANT");

        Role r3 = new Role();
        r3.setRolename("ROLE_CONTROLEUR");

        userDao.saveRole(r1);
        userDao.saveRole(r2);
        userDao.saveRole(r3);
    }

    public void createUsers() {
        User user1 = new User();
        user1.setUsername("boby");
        user1.setPassword("boby123");
        userDao.saveUser(user1, "ROLE_CHEF");

        User user2 = new User();
        user2.setUsername("hogy2");
        user2.setPassword("hogy123");
        userDao.saveUser(user2, "ROLE_ASSISTANT");

        User user3 = new User();
        user3.setUsername("jordan");
        user3.setPassword("jordan123");
        userDao.saveUser(user3, "ROLE_CONTROLEUR");
    }
}
