package com.buycar.controller.car;

import com.buycar.controller.AbstractTest;
import com.buycar.controller.util.CarInfoTest;
import com.buycar.controller.util.TestHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetAllCarTest extends AbstractTest {

    private final TestHelper testHelper = new TestHelper();
    private final ObjectMapper mapper = new ObjectMapper();
    private final TypeReference<List<CarInfoTest>> typeReference = new TypeReference<>() {
    };

    //test1
    @Test
    public void getAllUsers() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/car"))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();

        List<CarInfoTest> actual = mapper.readValue(contentAsString, typeReference);
        List<CarInfoTest> expected = testHelper.getAllCars();

        assertEquals("The wrong response is returned from request GET /car", expected, actual);
    }
}
