package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Id;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginAndPassword {
    @Id
    Long id;
    String login;
    String password;
}
