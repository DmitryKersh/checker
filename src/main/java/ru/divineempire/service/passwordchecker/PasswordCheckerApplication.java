package ru.divineempire.service.passwordchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class PasswordCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordCheckerApplication.class, args);
    }
}
