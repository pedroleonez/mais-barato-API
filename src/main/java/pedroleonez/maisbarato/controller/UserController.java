package pedroleonez.maisbarato.controller;

import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}