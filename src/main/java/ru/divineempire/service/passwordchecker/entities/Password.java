package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;


@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Password {
    @Id
    private Long id;
    private String password;
}
