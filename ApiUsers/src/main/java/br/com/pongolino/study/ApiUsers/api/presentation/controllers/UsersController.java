package br.com.pongolino.study.ApiUsers.api.presentation.controllers;

import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationRequest;
import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationResponse;
import br.com.pongolino.study.ApiUsers.api.services.UsersService;
import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/status/check")
    public String health() {
        return "Working";
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserCreationResponse createdUser = usersService.createUser(modelMapper.map(userCreationRequest, UserDto.class));

        return ResponseEntity.status(HttpStatusCode.valueOf(204)).body(createdUser);
    }
}

