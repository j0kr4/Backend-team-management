package fr.sdv.cnit.university.api.mapper;

import fr.sdv.cnit.university.api.dto.TeamDto;
import fr.sdv.cnit.university.api.entity.Team;

public class TeamMapper {
    public static TeamDto mapToDto(Team team){
        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .slogan(team.getSlogan())
                .build();
    }

    public static Team mapToEntity(TeamDto teamDto) {
        return Team.builder()
                .id(teamDto.getId())
                .name(teamDto.getName())
                .slogan(teamDto.getSlogan())
                .build();
    }
}
