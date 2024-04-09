package fr.sdv.cnit.university.api.mapper;

import fr.sdv.cnit.university.api.dto.PlayerDto;
import fr.sdv.cnit.university.api.entity.Player;

public class PlayerMapper {
    public static PlayerDto mapToDto(Player player){
        return PlayerDto.builder()
                .id(player.getId())
                .name(player.getName())
                .number(player.getNumber())
                .position(player.getPosition())
                .teamId(player.getTeamId())
                .build();
    }

    public static Player mapToEntity(PlayerDto playerDto) {
        return Player.builder()
                .id(playerDto.getId())
                .name(playerDto.getName())
                .number(playerDto.getNumber())
                .position(playerDto.getPosition())
                .teamId(playerDto.getTeamId())
                .build();
    }
}
