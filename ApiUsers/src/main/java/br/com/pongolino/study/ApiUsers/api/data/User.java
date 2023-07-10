package br.com.pongolino.study.ApiUsers.api.data;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tb_user")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 8181490636324479582L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String fullName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String UUID;
    @Column(nullable = false)
    private String encryptedPassword;
}

