package br.com.pongolino.study.ApiUsers.api.services.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3413159716887010646L;

    private final Long id;
    private final String fullName;
    private final String email;
    private final String UUID;
    private final String encryptedPassword;
}
