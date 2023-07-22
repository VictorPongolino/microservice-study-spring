package br.com.pongolino.study.ApiUsers.api.presentation.controllers;

import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationRequest;
import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationResponse;
import br.com.pongolino.study.ApiUsers.api.services.UserService;
import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/status/check")
    public String health() {
        return "Working";
    }

    @PostMapping("/user")
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<UserCreationRequest, UserDto> userDtoMapper = modelMapper.typeMap(UserCreationRequest.class, UserDto.class)
                .addMappings(mapper -> mapper.map(UserCreationRequest::getPassword, UserDto::setRawPassword));
        UserCreationResponse createdUser = userService.createUser(userDtoMapper.map(userCreationRequest));

        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(createdUser);
    }
}

