package com.buycar.controller.user;

import com.buycar.controller.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteUserTest extends AbstractTest {
    //test1
    @Test
    public void deleteUserByIdZeroTest() throws Exception {
        mockMvc.perform(delete("/user/0"))
                .andExpect(status().isBadRequest());
    }

    //test2
    @Test
    public void deleteUserByIdNotNumberTest() throws Exception {
        mockMvc.perform(delete("/user/test"))
                .andExpect(status().isBadRequest());
    }

    //test3
    @Test
    public void deleteUserByIdNotExistTest() throws Exception {
        mockMvc.perform(delete("/user/426"))
                .andExpect(status().isNotFound());
    }

    //test4
    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/1"))
                .andExpect(status().isNotFound());
    }
}
