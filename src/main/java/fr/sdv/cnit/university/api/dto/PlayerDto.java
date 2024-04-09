package fr.sdv.cnit.university.api.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PlayerDto {
    private Long id;

    private String name;

    private Integer number;

    private String position;

    private Integer teamId;
}
