package ru.divineempire.service.passwordchecker.web;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(makeFinal = true)
@RestController
public class ReplyController {
    private PasswordRepository passwordRepository;
    private LoginAndPasswordRepository loginAndPasswordRepository;

    @GetMapping(value = "/check", produces = MediaType.APPLICATION_JSON_VALUE)
    public Reply reply(@RequestParam("login") String login,
                       @RequestParam("password") String password) {
        if (login == null) {
            return new Reply(passwordRepository.countPasswords(password) == 0);
        } else {
            return new Reply(loginAndPasswordRepository.countLoginPasswords(login, password) == 0
                    && !login.equals(password)
            );
        }
         // return new Reply(true);
    }

    @GetMapping(value = "/checkraw")
    public String rawreply(@RequestParam("login") String login,
                       @RequestParam("password") String password) {
        return (loginAndPasswordRepository.countLoginPasswords(login, password) == 0
                && !login.equals(password))
                ? "true"
                : "false";
        // return new Reply(true);
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
