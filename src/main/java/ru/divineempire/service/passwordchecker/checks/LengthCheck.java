package ru.divineempire.service.passwordchecker.checks;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class LengthCheck implements BasicCheck {
    //private LengthCheckProperties lengthCheckProperties;

    private int min;
    private int max;
    private String NAME = "LENGTH_CHECK";

    @Override
    public CheckResult run(String login, @NonNull String password) {
        return password.length() < min || password.length() > max
                ? CheckResult.bad("Password must be from " + min + " to " + max + " characters long.")
                : CheckResult.fine();
    }

    @Override
    public String name() {
        return NAME;
    }
}
