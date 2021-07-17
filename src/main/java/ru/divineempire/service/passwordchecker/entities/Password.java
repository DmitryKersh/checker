package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("passwords")
public class Password {
    @Id
    Long id;

    String password;
}
