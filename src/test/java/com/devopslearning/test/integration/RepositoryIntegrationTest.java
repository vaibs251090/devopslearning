package com.devopslearning.test.integration;

import com.devopslearning.DevopslearningApplication;
import com.devopslearning.backend.persistence.domain.backend.Plan;
import com.devopslearning.backend.persistence.domain.backend.Role;
import com.devopslearning.backend.persistence.domain.backend.User;
import com.devopslearning.backend.persistence.domain.backend.UserRole;
import com.devopslearning.backend.persistence.repositories.PlanRepository;
import com.devopslearning.backend.persistence.repositories.RoleRepository;
import com.devopslearning.backend.persistence.repositories.UserRespository;
import com.devopslearning.enums.PlansEnum;
import com.devopslearning.enums.RolesEnum;
import com.devopslearning.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;
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



    @Test
    public void testCreateNewPlan(){
        Plan basicPlan = createBasicPlan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievePlan = planRepository.findById(PlansEnum.BASIC.getId()).orElseThrow(EntityNotFoundException::new);
        Assert.assertNotNull(retrievePlan);
    }

    public Plan createBasicPlan(PlansEnum plansEnum){
        return new Plan(plansEnum);
    }

    @Test
    public void testCreateNewRole(){
        Role role = createBasicRole(RolesEnum.ADMIN);
        roleRepository.save(role);
        Role retrieveRole = roleRepository.findById(RolesEnum.BASIC.getId()).orElseThrow(EntityNotFoundException::new);
        Assert.assertNotNull(retrieveRole);
    }

    public Role createBasicRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }

    @Test
    public void createNewUser(){
        User basicUser = createUser(PlansEnum.BASIC, RolesEnum.BASIC);
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

    @Test
    public void testDeleteUser() throws Exception{
        User basicUser = createUser(PlansEnum.PRO, RolesEnum.ADMIN);
        userRespository.deleteById(basicUser.getId());
    }
    public User createUser(PlansEnum plansEnum, RolesEnum rolesEnum){
        Plan plan = createBasicPlan(plansEnum);
        planRepository.save(plan);

        User basicUser = UserUtils.createBasicUser();
        basicUser.setPlan(plan);

        Role role = createBasicRole(rolesEnum);
        roleRepository.save(role);

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(basicUser, role));

        basicUser.getUserRoles().addAll(userRoles);
        basicUser = userRespository.save(basicUser);
        return basicUser;
    }

}
