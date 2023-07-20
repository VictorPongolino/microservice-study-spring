package br.com.pongolino.study.ApiUsers.api.services.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3413159716887010646L;

    private Long id;
    private String fullName;
    private String email;
    private String UUID;
    private String rawPassword;
    private String encryptedPassword;
}
