package br.com.pongolino.study.ApiUsers.api.presentation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreationRequest {
    @NotNull(message = "")
    @Size(min = 2, message = "")
    private final String fullName;
    @NotNull(message = "")
    @Email
    private final String email;
    @NotNull(message = "")
    @Size(min = 5, max = 16, message = "")
    private final String password;
}
