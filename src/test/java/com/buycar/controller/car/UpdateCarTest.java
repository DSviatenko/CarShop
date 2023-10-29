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
import static org.springframework.test.util.AssertionErrors.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UpdateCarTest extends AbstractTest {

    private final ObjectMapper mapper = new ObjectMapper();

    //test1
    @Test
    public void updateCarIdZeroTest() throws Exception {
        mockMvc.perform(post("/car/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_JSON))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void updateCarNotExistTest() throws Exception {
        mockMvc.perform(post("/car/415")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_JSON))
                .andExpect(status().isNotFound());
    }

    //test3
    @Test
    public void updateCarWithEmptyBodyTest() throws Exception {
        mockMvc.perform(post("/car/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    //test4
    @Test
    public void updateCarWithIdTest() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/car/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(TestHelper.CAR_WITH_ID_JSON, 4L)))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        CarInfoTest actual = mapper.readValue(contentAsString, CarInfoTest.class);
        CarInfoTest expected = new CarInfoTest(2L, 1L, "Mercedes-Benz", "E-class", 3200, Fuel.DIESEL, 2001, 5000);

        assertNotEquals("'id' should not be updated in request POST /car/{id}.", 4L, actual.id);
        assertEquals("All fields should be updated, except 'id' in request POST /car/{id}.", expected, actual);
    }

    //test5
    @Test
    public void updateCarTest() throws Exception {
        CarInfoTest expected = new CarInfoTest(4L, 2L, "Volkswagen", "Passat", 1998, Fuel.DIESEL, 2014, 10000);

        ResultActions resultActions = mockMvc.perform(post("/car/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestHelper.CAR_JSON))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        CarInfoTest actual = mapper.readValue(contentAsString, CarInfoTest.class);

        assertEquals("All fields should be updated in request POST /car/{id}.", expected, actual);
    }
}
