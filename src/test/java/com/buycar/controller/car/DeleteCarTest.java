package com.buycar.controller.car;

import com.buycar.controller.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteCarTest extends AbstractTest {

    //test1
    @Test
    public void deleteCarByIdZeroTest() throws Exception {
        mockMvc.perform(delete("/car/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void deleteCarByIdNotNumberTest() throws Exception {
        mockMvc.perform(delete("/car/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void deleteCarByIdNotExistTest() throws Exception {
        mockMvc.perform(delete("/car/426"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void deleteCarTest() throws Exception {
        mockMvc.perform(delete("/car/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/car/1"))
                .andExpect(status().isNotFound());
    }
}
