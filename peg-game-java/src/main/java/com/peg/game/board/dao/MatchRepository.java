package com.peg.game.board.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peg.game.board.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByPlayerId(Long playerId);
}
