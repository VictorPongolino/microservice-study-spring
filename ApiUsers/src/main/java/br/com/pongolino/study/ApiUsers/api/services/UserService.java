package br.com.pongolino.study.ApiUsers.api.services;

import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationResponse;
import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserCreationResponse createUser(UserDto userDto);
    Optional<UserDto> findByEmail(String email);
}

