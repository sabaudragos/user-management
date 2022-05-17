package sab.technologies.usermanagementservice.controller;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import sab.technologies.usermanagementservice.dto.User;
import sab.technologies.usermanagementservice.service.UserService;

import java.util.List;

@RequestMapping(value = "api/v1/user")
@RestController
public class UserController {

    private UserService userService;

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

    @GetMapping(value = "/getUserByClientId/{clientId}")
    public List<User> getUserByClientId(@PathVariable(value = "clientId") Long clientId) {
        return userService.getAllByClientId(clientId);
    }

    @GetMapping(value = "/getAllByClientIdAndProjectIdList")
    public List<User> getAllByClientIdAndProjectIdList(@RequestParam(value = "clientId") Long clientId,
                                                       @RequestParam(value = "projectIdList") List<Long> projectIdList) {
        return userService.getAllByClientIdAndProjectIdList(clientId, projectIdList);
    }

}
