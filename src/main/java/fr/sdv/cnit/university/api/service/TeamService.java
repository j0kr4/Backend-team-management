package fr.sdv.cnit.university.api.service;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;
import fr.sdv.cnit.university.api.mapper.PlayerMapper;
import fr.sdv.cnit.university.api.mapper.TeamMapper;
import fr.sdv.cnit.university.api.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import fr.sdv.cnit.university.api.repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    private final PlayerRepository playerRepository;

    public TeamService(
            TeamRepository teamRepository,
            PlayerRepository playerRepository
    ) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public Iterable<TeamDto> getTeams() {
        return teamRepository.findAll()
                .stream()
                .map(TeamMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TeamDto getTeam(Long id) { // Change this to Long
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeamDto not found with id : " + id));

        return TeamMapper.mapToDto(team);
    }

    public Iterable<PlayerDto> getPlayersFromTeam(Long id) { // Change this to Long
        return playerRepository.findPlayersByTeamId(id)
                .stream()
                .map(PlayerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TeamDto createTeam(TeamDto teamDto) throws TeamInvalidException {
        if (teamDto.getSlogan() == null) {
            throw new TeamInvalidException("Slogan is null");
        }

        Team team = teamRepository.save(TeamMapper.mapToEntity(teamDto));

        return TeamMapper.mapToDto(team);
    }

    public TeamDto editTeam(Long id, TeamDto teamDto) {
        Team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeamDto not found with id : " + id));

        existingTeam.setName(teamDto.getName());
        existingTeam.setSlogan(teamDto.getSlogan());

        Team team = teamRepository.save(existingTeam);

        return TeamMapper.mapToDto(team);
    }

    @Transactional
    public void deleteTeam(Long id) { // Change this to Long
        playerRepository.deletePlayersByTeamId(id);
        teamRepository.deleteById(id);
    }
}