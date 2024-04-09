package fr.sdv.cnit.university.api;

import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TeamServiceTest {
    @Autowired
    private TeamService teamService;

    @Test
    public void testGetTeams() {
        Iterable<TeamDto> teams = teamService.getTeams();
        assertNotNull(teams);
    }

    @Test
    public void testGetTeam() {
        TeamDto team = teamService.getTeam(1L);
        assertNotNull(team);
    }

    @Test
    public void testCreateTeam() {
        TeamDto team = TeamDto.builder()
                .name("Test Team")
                .build();
        TeamDto createdTeam = teamService.createTeam(team);
        assertNotNull(createdTeam);
        assertEquals("Test Team", createdTeam.getName());
    }

    @Test
    public void testEditTeam() {
        TeamDto team = TeamDto.builder()
                .name("Test Team")
                .build();
        TeamDto createdTeam = teamService.createTeam(team);
        createdTeam.setName("Updated Team");
        TeamDto updatedTeam = teamService.editTeam(createdTeam.getId(), createdTeam);
        assertNotNull(updatedTeam);
        assertEquals("Updated Team", updatedTeam.getName());
    }

    @Test
    public void testDeleteTeam() {
        TeamDto team = TeamDto.builder()
                .name("Test Team")
                .build();
        TeamDto createdTeam = teamService.createTeam(team);
        teamService.deleteTeam(createdTeam.getId());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            teamService.getTeam(createdTeam.getId());
        });
        assertNotNull(exception);
    }
}