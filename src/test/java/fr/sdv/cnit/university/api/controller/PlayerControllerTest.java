package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.entity.Player;
import fr.sdv.cnit.university.api.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class PlayerControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PlayerService playerService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetPlayers() throws Exception {
        mockMvc.perform(get("/players"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPlayer() throws Exception {
        mockMvc.perform(get("/players/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreatePlayer() throws Exception {
        Player player = new Player();
        player.setName("Test Player");

        mockMvc.perform(post("/players")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Player\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditPlayer() throws Exception {
        Player player = new Player();
        player.setName("Test Player");

        mockMvc.perform(put("/players/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Updated Player\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePlayer() throws Exception {
        mockMvc.perform(delete("/players/1"))
                .andExpect(status().isNoContent());
    }
}