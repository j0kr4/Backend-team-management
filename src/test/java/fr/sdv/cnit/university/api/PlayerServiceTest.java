package fr.sdv.cnit.university.api;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerServiceTest {
    @Autowired
    private PlayerService playerService;

    @Test
    public void testGetPlayers() {
        Iterable<PlayerDto> players = playerService.getPlayers();
        assertNotNull(players);
    }

    @Test
    public void testGetPlayer() {
        PlayerDto player = playerService.getPlayer(1L);
        assertNotNull(player);
    }

    @Test
    public void testCreatePlayer() {
        PlayerDto player = PlayerDto.builder()
                .name("Test Player")
                .build();
        PlayerDto createdPlayer = playerService.createPlayer(player);
        assertNotNull(createdPlayer);
        assertEquals("Test Player", createdPlayer.getName());
    }

    @Test
    public void testEditPlayer() {
        PlayerDto player = PlayerDto.builder()
                .name("Test Player")
                .build();
        PlayerDto createdPlayer = playerService.createPlayer(player);
        createdPlayer.setName("Updated Player");
        PlayerDto updatedPlayer = playerService.editPlayer(createdPlayer.getId(), createdPlayer);
        assertNotNull(updatedPlayer);
        assertEquals("Updated Player", updatedPlayer.getName());
    }

    @Test
    public void testDeletePlayer() {
        PlayerDto player = PlayerDto.builder()
                .name("Test Player")
                .build();
        PlayerDto createdPlayer = playerService.createPlayer(player);
        playerService.deletePlayer(createdPlayer.getId());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            playerService.getPlayer(createdPlayer.getId());
        });
        assertNotNull(exception);
    }
}