package com.devopslearning.test.integration;

import com.devopslearning.DevopslearningApplication;
import com.devopslearning.backend.persistence.domain.backend.Plan;
import com.devopslearning.backend.persistence.domain.backend.Role;
import com.devopslearning.backend.persistence.domain.backend.User;
import com.devopslearning.backend.persistence.domain.backend.UserRole;
import com.devopslearning.backend.persistence.repositories.PlanRepository;
import com.devopslearning.backend.persistence.repositories.RoleRepository;
import com.devopslearning.backend.persistence.repositories.UserRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DevopslearningApplication.class)
public class RepositoryIntegrationTest {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Before
    public void init(){
        Assert.assertNotNull(userRespository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(planRepository);
    }
    public static final int BASIC_PLAN_ID =1;
    public static final int BASIC_ROLE_ID =1;
    public static final int BASIC_ROLE_ID_2 =2;


    @Test
    public void testCreateNewPlan(){
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);
        Plan retrievePlan = planRepository.findById(BASIC_PLAN_ID).orElseThrow(EntityNotFoundException::new);
        Assert.assertNotNull(retrievePlan);
    }

    public Plan createBasicPlan(){
        Plan basicPlan = new Plan();
        basicPlan.setId(BASIC_PLAN_ID);
        basicPlan.setName("Basic");
        return basicPlan;
    }

    @Test
    public void testCreateNewRole(){
        Role basicRole = createBasicRole(BASIC_ROLE_ID_2);
        roleRepository.save(basicRole);
        Role retrieveRole = roleRepository.findById(BASIC_ROLE_ID_2).orElseThrow(EntityNotFoundException::new);
        Assert.assertNotNull(retrieveRole);
    }

    public Role createBasicRole(int role_id){
        Role basicRole = new Role();
        basicRole.setId(role_id);
        basicRole.setName("Basic");
        return basicRole;
    }

    @Test
    public void createNewUser(){
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole(BASIC_ROLE_ID);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setRole(basicRole);
        userRole.setUser(basicUser);
        userRoles.add(userRole);

        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole ur: userRoles) {
            roleRepository.save(ur.getRole());
        }

        basicUser = userRespository.save(basicUser);
        User createNewUser = userRespository.findById(basicUser.getId()).orElseThrow(EntityNotFoundException::new);
        Assert.assertTrue(createNewUser.getId() != 0);
        Assert.assertNotNull(createNewUser.getPlan());
        Assert.assertNotNull(createNewUser.getPlan().getId());
        Set<UserRole> newlyCreatedUsers = createNewUser.getUserRoles();
        for (UserRole ur: newlyCreatedUsers) {
            Assert.assertNotNull(ur.getRole());
            Assert.assertNotNull(ur.getRole().getId());
        }



        
    }

    public User createBasicUser(){
        User user = new User();
        user.setUsername("basicUser");
        user.setPassword("secret");
        user.setEmail("ma@gmail.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("12345567");
        user.setCountry("GB");
        user.setEnabled(true);
        user.setDescription("This is a description");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }
}
