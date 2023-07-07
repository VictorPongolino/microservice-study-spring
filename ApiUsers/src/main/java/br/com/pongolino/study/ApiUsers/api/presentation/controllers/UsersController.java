package br.com.pongolino.study.ApiUsers.api.presentation.controllers;

import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String health() {
        return "Working";
    }

    @PostMapping("/user")
    public String createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        return "Created user";
    }
}
