package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.entity.Player;
import fr.sdv.cnit.university.api.service.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<PlayerDto> getPlayers() {
        return (List<PlayerDto>) playerService.getPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerDto playerDto) {
        return playerService.createPlayer(playerDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDto> editPlayer(@PathVariable Long id, @RequestBody PlayerDto playerDetails) {
        PlayerDto playerDto = playerService.editPlayer(id, playerDetails);
        return ResponseEntity.ok(playerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}