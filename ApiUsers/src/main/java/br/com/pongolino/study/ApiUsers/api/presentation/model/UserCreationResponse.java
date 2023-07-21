package br.com.pongolino.study.ApiUsers.api.presentation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class UserCreationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6466356877785163946L;

    private String fullName;
    private String email;
    private String password;
}
