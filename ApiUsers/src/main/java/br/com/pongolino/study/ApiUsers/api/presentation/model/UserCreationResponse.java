package br.com.pongolino.study.ApiUsers.api.presentation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
public class UserCreationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6466356877785163946L;

    private final String fullName;
    private final String email;
    private final String password;
}
