package fr.sdv.cnit.university.api.service;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.entity.Player;
import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.mapper.PlayerMapper;
import fr.sdv.cnit.university.api.mapper.TeamMapper;
import jakarta.persistence.EntityNotFoundException;
import fr.sdv.cnit.university.api.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Iterable<PlayerDto> getPlayers() {
        return playerRepository.findAll()
            .stream()
            .map(PlayerMapper::mapToDto)
            .collect(Collectors.toList());
    }

    public PlayerDto getPlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        return PlayerMapper.mapToDto(player);
    }

    public PlayerDto createPlayer(PlayerDto playerDto) {
        Player player = playerRepository.save(PlayerMapper.mapToEntity(playerDto));

        return PlayerMapper.mapToDto(player);
    }

    public PlayerDto editPlayer(Long id, PlayerDto playerDto) {
        Player playerDetails = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));

        playerDetails.setName(playerDto.getName());
        playerDetails.setNumber(playerDto.getNumber());
        playerDetails.setPosition(playerDto.getPosition());
        playerDetails.setTeamId(playerDto.getTeamId());

        Player player = playerRepository.save(playerDetails);

        return PlayerMapper.mapToDto(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}