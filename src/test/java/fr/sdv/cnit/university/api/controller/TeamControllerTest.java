package fr.sdv.cnit.university.api.controller;

import fr.sdv.cnit.university.api.entity.Team;
import fr.sdv.cnit.university.api.exception.TeamInvalidException;
import fr.sdv.cnit.university.api.service.TeamService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamControllerTest {

    @Autowired
    private TeamController teamController;

    @MockBean
    private TeamService teamService;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(teamController);
    }

    @Test
    public void testGetTeams() {
        given()
                .when()
                .get("/teams")
                .then()
                .statusCode(200)
                .body("size()", is(not(0)));
    }

    @Test
    public void testGetTeam() {
        given()
                .when()
                .get("/teams/1")
                .then()
                .statusCode(200)
                .body("name", is(notNullValue()));
    }

    @Test
    public void testCreateTeam() {
        Team team = new Team();
        team.setName("Test Team");

        given()
                .contentType("application/json")
                .body(team)
                .when()
                .post("/teams")
                .then()
                .statusCode(200)
                .body("name", equalTo("Test Team"));
    }

    @Test
    public void testCreateTeamWithInvalidSlogan() {
        Team team = new Team();
        team.setName("Test Team");

        when(teamService.createTeam(team)).thenThrow(new TeamInvalidException("Slogan cannot be null or empty"));

        given()
                .contentType("application/json")
                .body(team)
                .when()
                .post("/teams")
                .then()
                .statusCode(400);
    }


    @Test
    public void testEditTeam() {
        Team team = new Team();
        team.setName("Updated Team");

        given()
                .contentType("application/json")
                .body(team)
                .when()
                .put("/teams/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Updated Team"));
    }

    @Test
    public void testDeleteTeam() {
        given()
                .when()
                .delete("/teams/1")
                .then()
                .statusCode(200);
    }
}