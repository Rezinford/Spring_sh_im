package com.example.demo.repositaty;

import com.example.demo.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerByPlayerName(String playerName);
    Optional<Player> findPlayerByEmail(String email);
    Optional<Player> findPlayerById(Long Id);
}
