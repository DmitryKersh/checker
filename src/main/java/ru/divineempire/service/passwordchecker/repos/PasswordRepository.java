package ru.divineempire.service.passwordchecker.repos;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.divineempire.service.passwordchecker.entities.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
    @Query(""" 
            select count(password) = 0 from passwords where password = :p
    """)
    Boolean countPasswords(
            @Param("p") String password
    );
}