package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.TestHelper;
import com.buycar.controller.util.UserInfoTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAllUserTest extends AbstractTest {

    private final TestHelper testHelper = new TestHelper();
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<List<UserInfoTest>> typeReference = new TypeReference<>() {};

    //test1
    @Test
    public void getAllUsers() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/user"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<UserInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<UserInfoTest> expected = testHelper.getAllUsers();

        assertEquals("The wrong response is returned from request GET /user", expected, actual);
    }
}
