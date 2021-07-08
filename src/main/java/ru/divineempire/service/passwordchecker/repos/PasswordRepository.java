package ru.divineempire.service.passwordchecker.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.divineempire.service.passwordchecker.entities.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
    Boolean checkPassword(String password);
}