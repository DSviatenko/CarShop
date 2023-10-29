package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.TestHelper;
import com.buycar.controller.util.UserInfoTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetUserTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void getUserByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/user/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void getUserByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/user/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void getUserByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/user/410"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void getUserByIdTest() throws Exception {

        ResultActions resultActions = mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        UserInfoTest actual = mapper.readValue(contentAsString, UserInfoTest.class);
        UserInfoTest expected = new TestHelper().getUserInfoById(1);

        assertEquals("The wrong response is returned from request GET /users/{id}", expected, actual);
    }
}
