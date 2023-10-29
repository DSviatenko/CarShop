package com.buycar.controller.car;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.CarInfoTest;
import com.buycar.controller.util.TestHelper;
import com.buycar.entity.Fuel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCarTest extends AbstractTest {

    ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void createCarEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    //test 2
    @Test
    public void createCarWithEmptyModelTest() throws Exception {
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_EMPTY_MODEL_JSON))
                .andExpect(status().isBadRequest());
    }

    //test 3
    @Test
    public void createCarWithEmptyUserTest() throws Exception {
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_EMPTY_USER_JSON))
                .andExpect(status().isBadRequest());
    }

    //test 4
    @Test
    public void createCarWithIncorrectUserTest() throws Exception {
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_INCORRECT_USER_JSON))
                .andExpect(status().isBadRequest());
    }

    //test 5
    @Test
    public void createCarWithIncorrectFuelTest() throws Exception {
        mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_INCORRECT_FUEL_JSON))
                .andExpect(status().isBadRequest());
    }

    //test 6
    @Test
    public void createCarTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        CarInfoTest actual = mapper.readValue(contentAsString, CarInfoTest.class);
        CarInfoTest expected = new CarInfoTest(6L, 2L, "Volkswagen", "Passat", 1998, Fuel.DIESEL, 2014, 10000);

        assertEquals("The wrong result is returned when requesting to create car", expected, actual);
    }
}
