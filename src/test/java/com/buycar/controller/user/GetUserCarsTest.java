package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.CarInfoTest;
import com.buycar.controller.util.TestHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetUserCarsTest extends AbstractTest {

    private final TestHelper testHelper = new TestHelper();
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<List<CarInfoTest>> typeReference = new TypeReference<>() {
    };

    //test1
    @Test
    public void getUserByIdZeroCarsTest() throws Exception {
        mockMvc.perform(get("/user/0/cars"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void getUserByIdNotNumberCarsTest() throws Exception {
        mockMvc.perform(get("/user/test/cars"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void getUserByIdNotExistCarsTest() throws Exception {
        mockMvc.perform(get("/user/410/cars"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void getUserCars() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/user/1/cars"))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        List<CarInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<CarInfoTest> expected = testHelper.getAllFirstUserCars();

        assertEquals("User`s car lists must be the same", expected, actual);
    }
}
