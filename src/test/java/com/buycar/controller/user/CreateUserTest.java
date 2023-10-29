package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.TestHelper;
import com.buycar.controller.util.UserInfoTest;
import com.buycar.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateUserTest extends AbstractTest {

    //test1
    @Test
    public void createUserEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void createUserWithEmptyNameTest() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void createUserWithIncorrectRoleTest() throws Exception {
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_INCORRECT_ROLE_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void createUserTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        UserInfoTest actual = new ObjectMapper().readValue(contentAsString, UserInfoTest.class);
        UserInfoTest expected = new UserInfoTest(6L, "Adam", Role.USER, "adam_23@gmail.com", "adam55");

        assertEquals("The wrong result is returned when requesting to create user.", expected, actual);
    }
}
