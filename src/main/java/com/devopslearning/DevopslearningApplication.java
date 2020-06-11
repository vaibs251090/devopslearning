package com.devopslearning;

import com.devopslearning.backend.persistence.domain.backend.Role;
import com.devopslearning.backend.persistence.domain.backend.User;
import com.devopslearning.backend.persistence.domain.backend.UserRole;
import com.devopslearning.backend.service.UserService;
import com.devopslearning.enums.PlansEnum;
import com.devopslearning.enums.RolesEnum;
import com.devopslearning.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopslearningApplication implements CommandLineRunner {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(DevopslearningApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DevopslearningApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user = UserUtils.createBasicUser();
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, new Role(RolesEnum.PRO)));
        LOG.debug("Creating user with username {}", user.getUsername());
        userService.createUser(user, PlansEnum.PRO, userRoles);
        LOG.info("User {} created", user.getUsername());
    }
}
