package ru.divineempire.service.passwordchecker.checks;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;
import ru.divineempire.service.passwordchecker.config.custom_properties.LengthCheckProperties;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class LengthCheck implements BasicCheck {
    private LengthCheckProperties lengthCheckProperties;
    private String NAME = "LENGTH_CHECK";

    @Override
    public CheckResult run(String login, @NonNull String password) {
        return (password.length() < lengthCheckProperties.getMin() || password.length() > lengthCheckProperties.getMax())
                ? CheckResult.bad("Password must be from " + lengthCheckProperties.getMin() + " to " +
                lengthCheckProperties.getMax() + " characters long.")
                : CheckResult.fine();
    }

    @Override
    public String name() {
        return NAME;
    }
}
