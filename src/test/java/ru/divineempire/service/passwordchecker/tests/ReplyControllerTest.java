package ru.divineempire.service.passwordchecker.tests;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class ReplyControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testReplyController() throws Exception {
        mvc.perform(
                get("/check?login=login&password=login"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value("false"));
        mvc.perform(
                get("/check?login=login&password=password123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valid").value("false"));

        mvc.perform(
                get("/check?login=login"))
                .andExpect(status().is4xxClientError());
        mvc.perform(
                get("/check?password=login"))
                .andExpect(status().is4xxClientError());
        mvc.perform(
                get("/check"))
                .andExpect(status().is4xxClientError());
    }
}
