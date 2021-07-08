package ru.divineempire.service.passwordchecker.entities;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checker_passwords")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Password {

    private Long id;
    private String password;

    public void setId(final Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
