package sab.technologies.usermanagementservice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sab.technologies.usermanagementservice.UserConfig;
import sab.technologies.usermanagementservice.UserManagementServiceApplication;
import sab.technologies.usermanagementservice.dto.User;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTest {

    private static final String password = "password";
    private static final String firstName = "John";
    private static final String lastName = "Doe";
    private static final String email = "test@test.com";
    private static final Long clientId = 12345L;
    private static final ArrayList projectIdList = new ArrayList<>(Arrays.asList("111", "222"));

    @Autowired
    private UserService userService;

    @Test
    void createUser() {
        //when
        User savedUser = createAndSaveUser();

        //then
        User findUser = userService.getUser(savedUser.getId());
        Assert.assertNotNull("There should be one user saved in database", savedUser);
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getAllByClientId() {
    }

    @Test
    void getAllByClientIdAndProjectIdList() {
    }

    private User createAndSaveUser() {
        User user = new User();
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setClientId(clientId);
        user.setProjectIdList(projectIdList);

        return userService.createUser(user);

    }
}