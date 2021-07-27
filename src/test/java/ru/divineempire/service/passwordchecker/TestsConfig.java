package ru.divineempire.service.passwordchecker;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

@Profile("test")
@Configuration
public class TestsConfig {
    @Bean
    @Primary
    public LoginAndPasswordRepository loginAndPasswordRepositoryMock() {
        return Mockito.mock(LoginAndPasswordRepository.class);
    }

    @Bean
    @Primary
    public PasswordRepository passwordRepositoryMock() {
        return Mockito.mock(PasswordRepository.class);
    }
}
