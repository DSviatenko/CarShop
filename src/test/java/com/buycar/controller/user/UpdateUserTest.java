package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.TestHelper;
import com.buycar.controller.util.UserInfoTest;
import com.buycar.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateUserTest extends AbstractTest {

    private final TestHelper testHelper = new TestHelper();
    private final ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void updateUserIdZeroTest() throws Exception {
        mockMvc.perform(post("/user/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_JSON))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void updateUserNotExistTest() throws Exception {
        mockMvc.perform(post("/user/415")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_JSON))
                .andExpect(status().isNotFound());
    }

    //test3
    @Test
    public void updateUserInvalidNameTest() throws Exception {
        UserInfoTest playerInfoTest = testHelper.getUserInfoById(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/" + playerInfoTest.id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_EMPTY_NAME_JSON))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void updateUserWithIdTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/user/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(TestHelper.USER_WITH_ID_JSON, 8L)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        UserInfoTest actual = mapper.readValue(contentAsString, UserInfoTest.class);
        UserInfoTest expected = mapper.readValue(String.format(TestHelper.USER_WITH_ID_JSON, 5), UserInfoTest.class);

        assertNotEquals("'id' should not be updated in request POST /user/{id}.", 8, actual.id);
        assertEquals("All fields should be updated, except 'id' in request POST /user/{id}.", expected, actual);
    }

    //test5
    @Test
    public void updatePlayerEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/user/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    //test6
    @Test
    public void updateUserWithInvalidEmail() throws Exception {
        mockMvc.perform(post("/user/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_INCORRECT_EMAIL_JSON))
                .andExpect(status().isBadRequest());
    }

    //test7
    @Test
    public void updateUserTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/user/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.USER_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        UserInfoTest actual = mapper.readValue(contentAsString, UserInfoTest.class);
        UserInfoTest expected = new UserInfoTest(3L, "Adam", Role.USER, "adam_23@gmail.com", "adam55");

        assertEquals("All fields should be updated in request POST /user/{id}.", expected, actual);
    }
}
