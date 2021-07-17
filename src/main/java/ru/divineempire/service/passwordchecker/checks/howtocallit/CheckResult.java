package ru.divineempire.service.passwordchecker.checks.howtocallit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(makeFinal = true)
public class CheckResult {
    public static CheckResult fine(){
        return new CheckResult(true, "");
    }

    public static CheckResult bad(String message){
        return new CheckResult(false, message);
    }

    @Getter
    boolean passed;

    @Getter
    String message;
}
