package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "checker_data")
public class LoginAndPassword {
    Long id;
    String login;
    String password;

    public void setId(final Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
