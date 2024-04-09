package fr.sdv.cnit.university.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TeamDto {
    private Long id;

    private String name;

    private String slogan;
}
