package com.vcg.zombie.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcg.zombie.entity.Human;
import com.vcg.zombie.entity.Zombie;
import com.vcg.zombie.service.WorldService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WorldControllerTest {

    @InjectMocks WorldController userController;
    private MockMvc mockMvc;
    @MockBean
    WorldService worldService;

    String jsonString =
        " {\"gridSize\":\"5\",\"zombie\":{\"x\":\"4\",\"y\":\"5\"},\"creatures\":[{\"x\":\"1\",\"y\":\"1\"},{\"x\":\"2\",\"y\":\"3\"},{\"x\":\"3\",\"y\":\"4\"}],\"commands\":\"R\"}";


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WorldController(worldService)).build();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(worldService);
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/z"))
            .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void create() throws Exception {
        String string = jsonString;

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("objectMapper = " + objectMapper.writeValueAsString(string));
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/zombie").contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(string)).accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());

        // .andExpect(MockMvcResultMatchers.status().is(201));


        // MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
        //                                           .post("/zombie")
        //                                           .contentType(MediaType.APPLICATION_JSON)
        //                                           .content(jsonString))
        //                           .andReturn();
        // System.out.println(mvcResult.getResponse().getContentAsString());
    }
    Map<String, Object> createMap() {
        Map<String, Object> body = new HashMap<>();
        body.put("gridSize", "5");
        body.put("zombie", new Zombie(1, 1));
        body.put("creatures", List.of(new Human(2, 2), new Human(3, 3)));
        body.put("commands", "RRRRRR");
        return body;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}