package sab.technologies.usermanagementservice.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import sab.technologies.usermanagementservice.dto.User;
import sab.technologies.usermanagementservice.service.UserService;

import java.util.List;

@RequestMapping(value = "api/v1/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User createUser(@RequestBody @NonNull User user) {
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody @NonNull User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/client/{clientId}")
    public List<User> getUserListByClientId(@PathVariable(value = "clientId") Long clientId) {
        return userService.getAllByClientId(clientId);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
