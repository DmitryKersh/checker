package ru.divineempire.service.passwordchecker.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.divineempire.service.passwordchecker.entities.LoginAndPassword;

@Repository
public interface LoginAndPasswordRepository extends CrudRepository<LoginAndPassword, Long> {
    //Boolean checkLoginAndPassword(String login, String password);
}
