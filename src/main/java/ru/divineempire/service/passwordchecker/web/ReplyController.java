package ru.divineempire.service.passwordchecker.web;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.CheckResult;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true)
@RestController
public class ReplyController {
    private List<BasicCheck> checksList;


    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reply reply(@RequestParam("login") String login,
                       @RequestParam("password") String password) {
        int successes = 0;
        int fails = 0;
        List<String> errorMessages = new LinkedList<>();
        List<String> successfulChecks = new LinkedList<>();
        List<String> failedChecks = new LinkedList<>();

        for (BasicCheck check : checksList) {
            CheckResult result = check.run(login, password);

            if (result.isPassed()) {
                successes++;
                successfulChecks.add(check.name());
            }
            else {
                fails++;
                failedChecks.add(check.name());
            }

            if (!result.getMessage().isEmpty())
                errorMessages.add(result.getMessage());
        }
        return new Reply(fails == 0, successes, fails, successfulChecks, failedChecks, errorMessages);
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
