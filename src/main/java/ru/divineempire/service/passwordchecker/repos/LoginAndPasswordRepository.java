package ru.divineempire.service.passwordchecker.repos;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.divineempire.service.passwordchecker.entities.LoginAndPassword;

@Repository
public interface LoginAndPasswordRepository extends CrudRepository<LoginAndPassword, Long> {
    @Query(""" 
            select count(*) from login_and_passwords where(login = :l and password = :p)
    """)
    Integer countLoginPasswords(
            @Param("l") String login,
            @Param("p") String password
    );
}
