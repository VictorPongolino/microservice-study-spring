package br.com.pongolino.study.ApiUsers.api.services;

import br.com.pongolino.study.ApiUsers.api.presentation.model.UserCreationResponse;
import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;

public interface UsersService {
    UserCreationResponse createUser(UserDto userDto);
}

