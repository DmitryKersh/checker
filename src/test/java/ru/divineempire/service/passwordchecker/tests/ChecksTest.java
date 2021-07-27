package ru.divineempire.service.passwordchecker.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.divineempire.service.passwordchecker.checks.DatabaseCheck;
import ru.divineempire.service.passwordchecker.checks.LengthCheck;
import ru.divineempire.service.passwordchecker.checks.LoginAsSubstringCheck;
import ru.divineempire.service.passwordchecker.checks.SpecialSymbolsCheck;
import ru.divineempire.service.passwordchecker.checks.howtocallit.BasicCheck;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;

@ActiveProfiles("test")
@SpringBootTest
class ChecksTest {
    @Autowired
    private LoginAndPasswordRepository loginAndPasswordRepositoryMock;

    @Autowired
    private PasswordRepository passwordRepositoryMock;

    @Test
    void testDatabaseCheck() {
        // 0
        Mockito.when(loginAndPasswordRepositoryMock
                .checkLoginPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);

        // 1
        Mockito.when(loginAndPasswordRepositoryMock
                .checkLoginPassword(ArgumentMatchers.anyString(), ArgumentMatchers.contains("qwerty")))
                .thenReturn(Boolean.FALSE);

        // 2
        Mockito.when(loginAndPasswordRepositoryMock
                .checkLoginPassword(ArgumentMatchers.eq("GoodLogin"), ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);

        // 3
        Mockito.when(loginAndPasswordRepositoryMock
                .checkLoginPassword(ArgumentMatchers.anyString(), ArgumentMatchers.eq("VerySafePassword")))
                .thenReturn(Boolean.TRUE);

        // 4
        Mockito.when(passwordRepositoryMock.checkPassword(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);

        // 5
        Mockito.when(passwordRepositoryMock.checkPassword(ArgumentMatchers.contains("123"))).thenReturn(Boolean.FALSE);

        //----------------------------------------------------------------------------------------------------
        BasicCheck check = new DatabaseCheck(passwordRepositoryMock, loginAndPasswordRepositoryMock);

        Assertions.assertTrue(check.run("login", "VerySafePassword").isPassed()); // (3)
        Assertions.assertTrue(check.run("login", "1q2w3e").isPassed()); // (0)
        Assertions.assertTrue(check.run("GoodLogin", "qwerty").isPassed()); //(2)

        Assertions.assertFalse(check.run("login", "123").isPassed()); // (5)
        Assertions.assertFalse(check.run("login", "qwerty").isPassed()); // (1)
    }

    @Test
    void testLengthCheck() {
        BasicCheck check = new LengthCheck(4, 10);

        Assertions.assertFalse(check.run("login", "aaa").isPassed());
        Assertions.assertFalse(check.run("login", "aaaaaaaaaaaaaaaa").isPassed());
        Assertions.assertFalse(check.run("login", "").isPassed());

        Assertions.assertTrue(check.run("login", "aaaa").isPassed());
        Assertions.assertTrue(check.run("login", "aaaaaaaaaa").isPassed());
        Assertions.assertTrue(check.run("login", "aaaaaaa").isPassed());
    }

    @Test
    void testSpecialSymbolsCheck() {
        BasicCheck check = new SpecialSymbolsCheck();

        Assertions.assertTrue(check.run("login", "pAS$w0rd").isPassed());

        Assertions.assertFalse(check.run("login", "pASSword").isPassed()); // no special symbols
        Assertions.assertFalse(check.run("login", "p@$$word").isPassed()); // no UPPER-CASE
        Assertions.assertFalse(check.run("login", "password").isPassed()); // no anything
    }

    @Test
    void testLoginAsSubstring() {
        BasicCheck check = new LoginAsSubstringCheck();

        Assertions.assertTrue(check.run("login", "password").isPassed());
        Assertions.assertTrue(check.run("login", "l-o-g-i-n").isPassed());

        Assertions.assertFalse(check.run("login", "login").isPassed());
        Assertions.assertFalse(check.run("log", "login").isPassed());
        Assertions.assertFalse(check.run("login", "log").isPassed());
        Assertions.assertFalse(check.run("login", "logloginloglog").isPassed());
    }
}
