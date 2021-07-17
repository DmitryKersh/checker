package ru.divineempire.service.passwordchecker.repos;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.divineempire.service.passwordchecker.entities.LoginAndPassword;

@Repository
public interface LoginAndPasswordRepository extends CrudRepository<LoginAndPassword, Long> {
    @Query(""" 
            SELECT count(*) = 0 FROM login_and_passwords WHERE (login = :l AND password = :p)
             """)
    Boolean checkLoginPassword(
            @Param("l") String login,
            @Param("p") String password
    );
}
