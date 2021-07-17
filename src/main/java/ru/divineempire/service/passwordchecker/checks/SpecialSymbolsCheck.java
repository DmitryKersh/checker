package ru.divineempire.service.passwordchecker.checks;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;

import java.util.regex.Pattern;

@FieldDefaults(makeFinal = true)
public class SpecialSymbolsCheck implements BasicCheck {
    private String NAME = "CAPITALS_AND_SPECIALS_CHECK";
    private String CAPITALS = ".*[A-Z].*";
    private String CAPITALS_ERROR_MSG = "Password must contain at least one upper-case character.";

    private String SPECIALS = ".*[\\\\!@#$%^&*<>{}\\[\\]].*";
    private String SPECIALS_ERROR_MSG = "Password must contain at least one character from: !@#$%^&*<>{}[] .";

    @Override
    @NonNull public CheckResult run(String login, @NonNull String password) {
        String msg = "";
        boolean verdict = true;

        if (!Pattern.matches(CAPITALS, password))
            return CheckResult.bad(CAPITALS_ERROR_MSG);

        if (!Pattern.matches(SPECIALS, password))
            return CheckResult.bad(SPECIALS_ERROR_MSG);

        return CheckResult.fine();
    }

    @Override
    public String name() {
        return NAME;
    }
}
