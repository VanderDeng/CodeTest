package com.vcg.zombie.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vcg.zombie.entity.Human;
import com.vcg.zombie.entity.World;
import com.vcg.zombie.entity.Zombie;
import com.vcg.zombie.service.WorldService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private MockMvc mockMvc;
    @MockBean
    WorldService worldService;

    String jsonString = """
        {
            "gridSize": "5",
            "zombie": {
                "x": "4",
                "y": "5"
            },
            "creatures": [
                {
                    "x": "1",
                    "y": "1"
                },
                {
                    "x": "2",
                    "y": "3"
                },
                {
                    "x": "3",
                    "y": "4"
                }
            ],
            "commands": "1"
        }""";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new WorldController(worldService)).build();
        when(worldService.getWorld()).thenReturn(new World());
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
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/zombie").contentType(MediaType.APPLICATION_JSON)
                            .content(jsonString)
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());

        var string2 = createWorldInstance();
        mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.POST, "/zombie").contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(string2))
                            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isCreated());


        // MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
        //                                           .post("/zombie")
        //                                           .contentType(MediaType.APPLICATION_JSON)
        //                                           .content(jsonString))
        //                           .andReturn();
        // System.out.println(mvcResult.getResponse().getContentAsString());
    }

    private World createWorldInstance() {
        return new World(5, new Zombie(4, 5), List.of(new Human(1, 1), new Human(2, 3), new Human(3, 4)), "R");

        // World testWorld = new World();
        // testWorld.setZombie(new Zombie(1, 1));
        // testWorld.setGridSize(10);
        // testWorld.setCommands("RDRU");
        // testWorld.setCreatures(List.of(new Human(2, 2), new Human(3, 3)));
        // return testWorld;
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