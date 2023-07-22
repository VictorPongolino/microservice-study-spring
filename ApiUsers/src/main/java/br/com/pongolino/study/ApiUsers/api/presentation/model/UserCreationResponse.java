package br.com.pongolino.study.ApiUsers.api.presentation.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter @Setter
public class UserCreationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 6466356877785163946L;

    private String UUID;
    private String fullName;
    private String email;
}
