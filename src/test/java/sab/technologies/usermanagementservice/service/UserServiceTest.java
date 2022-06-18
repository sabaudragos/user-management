package sab.technologies.usermanagementservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sab.technologies.usermanagementservice.UserConfig;
import sab.technologies.usermanagementservice.dto.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Import(UserConfig.class)
@SpringBootTest
class UserServiceTest {

    private static final String PASSWORD = "password";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "test@test.com";
    private static final String NEW_EMAIL = "new@email.com";
    private static final Long CLIENT_ID = 12345L;

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUser() {
        //when
        User savedUser = createAndSaveUser();
        User findUser = userService.getUser(savedUser.getId());

        // then
        Assertions.assertNotNull(findUser, "There should be one user saved in database");
        Assertions.assertEquals(EMAIL, findUser.getEmail(), "The email should have matched");
        Assertions.assertEquals(CLIENT_ID, findUser.getClientId(), "The client id should have matched");
    }

    @Test
    void shouldGetUser() {
        // given
        User savedUser = createAndSaveUser();

        //when
        User findUser = userService.getUser(savedUser.getId());

        // then
        Assertions.assertNotNull(findUser, "There should be one user saved in database");
        Assertions.assertEquals(EMAIL, findUser.getEmail(), "The email should have matched");
        Assertions.assertEquals(CLIENT_ID, findUser.getClientId(), "The client id should have matched");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFoundInDb() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getUser(101010L),
                "Should have thrown EntityNotFoundException when user not in db");

    }

    @Test
    void updateUser() {
        // given
        User savedUser = createAndSaveUser();
        savedUser.setEmail(NEW_EMAIL);

        //when
        User updatedUser = userService.updateUser(savedUser);

        // then
        Assertions.assertNotNull(updatedUser, "There should be one user saved in database");
        Assertions.assertEquals(NEW_EMAIL, updatedUser.getEmail(), "The email should have matched");
        Assertions.assertEquals(CLIENT_ID, updatedUser.getClientId(), "The client id should have matched");
    }

    @Test
    void shouldDeleteUser() {
        // given
        User savedUser = createAndSaveUser();

        //when
        userService.deleteUser(savedUser.getId());

        // then
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userService.getUser(savedUser.getId()),
                "The user should have been removed from the database");
    }

    @Test
    void getAllByClientId() {
        // given
        User savedUserOne = createAndSaveUser();
        User savedUserTwo = createAndSaveUser("some@emai.com");

        //when
        List<User> userList = userService.getAllByClientId(savedUserOne.getClientId());

        // then
        Assertions.assertEquals(2, userList.size(), "There should be 2 users in the db");
        userList.forEach(user ->
                Assertions.assertEquals(CLIENT_ID, user.getClientId(), "The client id should have matched"));
    }


    private User createAndSaveUser() {
        User user = new User();
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        user.setClientId(CLIENT_ID);

        return userService.createUser(user);
    }

    private User createAndSaveUser(String email) {
        User user = new User();
        user.setPassword(PASSWORD);
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(email);
        user.setClientId(CLIENT_ID);

        return userService.createUser(user);
    }
}