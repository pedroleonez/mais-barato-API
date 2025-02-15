package pedroleonez.maisbarato.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.model.UserModel;
import pedroleonez.maisbarato.service.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}