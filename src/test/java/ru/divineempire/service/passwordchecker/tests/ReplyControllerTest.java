package ru.divineempire.service.passwordchecker.tests;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.divineempire.service.passwordchecker.repos.LoginAndPasswordRepository;
import ru.divineempire.service.passwordchecker.repos.PasswordRepository;
import ru.divineempire.service.passwordchecker.web.ReplyController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PasswordRepository passwordRepositoryMock;
    @MockBean
    private LoginAndPasswordRepository loginAndPasswordRepositoryMock;

    @Test
    public void testReplyController() throws Exception {
        Mockito.when(passwordRepositoryMock.checkPassword(ArgumentMatchers.anyString())).thenReturn(true);
        Mockito.when(loginAndPasswordRepositoryMock
                .checkLoginPassword(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(true);

        mockMvc.perform(
                get("/check?login=login&password=login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value("true"));
        mockMvc.perform(
                get("/check?login=login"))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(
                get("/check?password=login"))
                .andExpect(status().is4xxClientError());
        mockMvc.perform(
                get("/check"))
                .andExpect(status().is4xxClientError());
    }
}
