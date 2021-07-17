package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("login_and_passwords")
public class LoginAndPassword {
    @Id
    Long id;

    String login;
    String password;
}
