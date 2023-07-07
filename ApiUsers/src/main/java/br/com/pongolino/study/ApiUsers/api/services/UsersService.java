package br.com.pongolino.study.ApiUsers.api.services;

import br.com.pongolino.study.ApiUsers.api.services.model.UserDto;

public interface UsersService {
    UserDto createUser(UserDto userDto);
}

