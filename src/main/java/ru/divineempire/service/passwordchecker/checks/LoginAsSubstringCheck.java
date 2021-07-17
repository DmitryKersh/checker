package ru.divineempire.service.passwordchecker.checks;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;

@FieldDefaults(makeFinal = true)
public class LoginAsSubstringCheck implements BasicCheck {
    private String NAME = "LOGIN_AS_SUBSTRING_CHECK";
    private String ERROR_MSG = "Password shouldn't contain login (or vice versa).";

    @Override
    @NonNull public CheckResult run(String login, @NonNull String password) {
        return password.contains(login) || login.contains(password)
                ? CheckResult.bad(ERROR_MSG)
                : CheckResult.fine();
        }

    @Override
    public String name() {
        return NAME;
    }
}

