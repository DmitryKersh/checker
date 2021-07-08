package ru.divineempire.service.passwordchecker.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.divineempire.service.passwordchecker.web.Reply;

@RestController
public class ReplyController {

    @GetMapping("/check")
    public Reply reply(@RequestParam(value = "login") String login,
                       @RequestParam(value = "password") String password) {
        // logic
        return login.equals(password) ? new Reply(Boolean.FALSE) : new Reply(Boolean.TRUE);
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
