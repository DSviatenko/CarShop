package com.buycar.controller.car;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.CarInfoTest;
import com.buycar.controller.util.TestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetCarTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void getCarByIdEqualZeroTest() throws Exception {
        mockMvc.perform(get("/car/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void getCarByIdNotNumberTest() throws Exception {
        mockMvc.perform(get("/car/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void getCarByIdNotExistTest() throws Exception {
        mockMvc.perform(get("/car/410"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void getCarTest() throws Exception {
        ResultActions actions = mockMvc.perform(get("/car/2"))
                .andExpect(status().isOk());

        String contentAsString = actions.andReturn().getResponse().getContentAsString();

        CarInfoTest actual = mapper.readValue(contentAsString, CarInfoTest.class);
        CarInfoTest expected = new TestHelper().getCarInfoById(2);

        assertEquals("The wrong response is returned from request GET /car/{id}", expected, actual);
    }
}
