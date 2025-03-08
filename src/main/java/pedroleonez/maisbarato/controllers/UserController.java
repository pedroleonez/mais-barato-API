package pedroleonez.maisbarato.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pedroleonez.maisbarato.infra.security.GetLoggedUserService;
import pedroleonez.maisbarato.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final GetLoggedUserService getLoggedUserService;

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("success");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<String> getLoggedUser() {
        String username = getLoggedUserService.getLoggedUser();

        return ResponseEntity.ok(username);
    }
}