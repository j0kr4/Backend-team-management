package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.entity.Player;
import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fr.sdv.cnit.university.api.service.TeamService;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = "/teams", method = RequestMethod.GET)
    public Iterable<TeamDto> getTeams() {
        return teamService.getTeams();
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.GET)
    public TeamDto getTeam(
            @PathVariable("id") Long id
    ) {
        return teamService.getTeam(id);
    }

    @RequestMapping(value = "/teams/{id}/players", method = RequestMethod.GET)
    public Iterable<PlayerDto> getPlayersFromTeam(
            @PathVariable("id") Long id
    ) {
        return teamService.getPlayersFromTeam(id);
    }

    @RequestMapping(value = "/teams", method = RequestMethod.POST)
    public TeamDto createTeam(
            @RequestBody TeamDto team
    ) {
        return teamService.createTeam(team);
    }

    @ExceptionHandler(TeamInvalidException.class)
    public ResponseEntity<String> handleTeamInvalidException(TeamInvalidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.PATCH)
    public TeamDto editTeam(
            @PathVariable("id") Long id,
            @RequestBody TeamDto team
    ) {
        return teamService.editTeam(id, team);
    }

    @RequestMapping(value = "/teams/{id}", method = RequestMethod.DELETE)
    public void deleteTeam(
            @PathVariable("id") Long id
    ) {
        teamService.deleteTeam(id);
    }
}
