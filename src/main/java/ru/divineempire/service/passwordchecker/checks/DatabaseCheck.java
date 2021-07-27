package ru.divineempire.service.passwordchecker.checks;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class DatabaseCheck implements BasicCheck {
    private PasswordRepository passwordRepository;
    private LoginAndPasswordRepository loginAndPasswordRepository;

    private String NAME = "DATABASE_CHECK";
    private String PASSWORD_ERROR_MSG = "This password can be found in open-access databases in the Internet." +
            " Consider another one.";
    private String LOGIN_PASSWORD_ERROR_MSG = "This login-password pair can be found in open-access databases " +
            "in the Internet. Consider another one.";

    @Override
    public CheckResult run(String login, @NonNull String password) {
        if (!passwordRepository.checkPassword(password)) {
            return CheckResult.bad(PASSWORD_ERROR_MSG);
        }

        if (!loginAndPasswordRepository.checkLoginPassword(login, password)) {
            return CheckResult.bad(LOGIN_PASSWORD_ERROR_MSG);
        }

        return CheckResult.fine();
    }

    @Override
    public String name() {
        return NAME;
    }
}
