package fr.sdv.cnit.university.api.repository;

import fr.sdv.cnit.university.api.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> { // Change this to Long
    List<Player> findPlayersByTeamId(Long teamId);

    Long deletePlayersByTeamId(Long teamId);
}