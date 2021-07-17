package ru.divineempire.service.passwordchecker.web;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.divineempire.service.passwordchecker.checks.LengthCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.DatabaseCheck;
import ru.divineempire.service.passwordchecker.checks.LoginAsSubstringCheck;
import ru.divineempire.service.passwordchecker.checks.SpecialSymbolsCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;
import ru.divineempire.service.passwordchecker.config.custom_properties.LengthCheckProperties;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true)
@RestController
public class ReplyController {
    private PasswordRepository passwordRepository;
    private LoginAndPasswordRepository loginAndPasswordRepository;
    private LengthCheckProperties lengthCheckProperties;


    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reply reply(@RequestParam("login") String login,
                       @RequestParam("password") String password) {
        List<BasicCheck> checks = Arrays.asList(
                new LoginAsSubstringCheck(),
                new SpecialSymbolsCheck(),
                new DatabaseCheck(passwordRepository, loginAndPasswordRepository),
                new LengthCheck(lengthCheckProperties)
        );

        int successes = 0;
        int fails = 0;
        List<String> errorMessages = new LinkedList<>();
        List<String> successfulChecks = new LinkedList<>();
        List<String> failedChecks = new LinkedList<>();

        for (BasicCheck check : checks) {
            CheckResult result = check.run(login, password);

            if (result.isPassed()) {
                successes++;
                successfulChecks.add(check.name());
            }
            else {
                fails++;
                failedChecks.add(check.name());
            }

            errorMessages.add(result.getMessage());
        }
        return new Reply(fails == 0, successes, fails, successfulChecks, failedChecks, errorMessages);
    }

    /*
        @GetMapping(value = "/checkraw")
        public String rawreply(@RequestParam("login") String login,
                               @RequestParam("password") String password) {
            return (loginAndPasswordRepository.checkLoginPassword(login, password)
                    && !login.equals(password))
                    ? "true"
                    : "false";
        }
    */
    @GetMapping("/error")
    public String error() {
        return "error";

    }
}
