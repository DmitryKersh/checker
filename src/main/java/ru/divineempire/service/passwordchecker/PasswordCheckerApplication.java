package ru.divineempire.service.passwordchecker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import ru.divineempire.service.passwordchecker.entities.Password;

@SpringBootApplication
public class PasswordCheckerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PasswordCheckerApplication.class, args);
    }
}
