package ru.divineempire.service.passwordchecker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.divineempire.service.passwordchecker.checks.DatabaseCheck;
import ru.divineempire.service.passwordchecker.checks.LengthCheck;
import ru.divineempire.service.passwordchecker.checks.LoginAsSubstringCheck;
import ru.divineempire.service.passwordchecker.checks.SpecialSymbolsCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.config.custom_properties.LengthCheckProperties;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ChecksConfig {
    @Bean
    List<BasicCheck> checksList(
        PasswordRepository passwordRepository,
        LoginAndPasswordRepository loginAndPasswordRepository,
        LengthCheckProperties lengthCheckProperties
    ){
        return Arrays.asList(
                new LoginAsSubstringCheck(),
                new SpecialSymbolsCheck(),
                new DatabaseCheck(passwordRepository, loginAndPasswordRepository),
                new LengthCheck(lengthCheckProperties.getMin(), lengthCheckProperties.getMax())
        );
    }
}
