package ru.divineempire.service.passwordchecker.checks.howtocallit;

import lombok.NonNull;

public interface BasicCheck {
    CheckResult run(String login, @NonNull String password);
    String name();
}
