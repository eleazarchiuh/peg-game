package com.peg.game.board.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.peg.game.board.dao.MatchRepository;
import com.peg.game.board.dao.PlayerRepository;
import com.peg.game.board.entity.Match;
import com.peg.game.board.entity.Player;
import com.peg.game.board.exceptions.NotFoundException;
import com.peg.game.board.model.Board;
import com.peg.game.board.model.Movement;
import com.peg.game.board.model.Node;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

    private PlayerRepository playerRepository;
    private MatchRepository matchRepository;

    public BoardService(PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    // en java por cada @Service es singleton
    // pero tambien se puede manejar estado por usuario
    // para efecto del ejercicio se deja es estado global
    private Map<Long,Board> boardMap = new HashMap<>();

    @Transactional
    public Board createNew(String name) {
        Board newBoard = createBoard();
        Player player = saveMatchInitializationAndReturnPlayer(name);
        newBoard.setPlayerId(player.getId());
        this.boardMap.put(player.getId(), newBoard);
        return newBoard;
    }

    public Board getBoardByPlayerId(Long id) {
        if(boardMap.get(id) == null) {
            throw new NotFoundException("Board not initialized");
        }
        return boardMap.get(id);
    }

    @Transactional
    public Board movement(Long id, Movement movement) {
        if(boardMap.get(id) == null) {
            throw new NotFoundException("Board not initialized");
        }
        Board board = boardMap.get(id);
        Player user = playerRepository.findById(id).orElseThrow(() -> new NotFoundException("Player not found"));
        Node origin = board.getNode(movement.getOriginX(), movement.getOriginY());
        Node intermediate = board.getNode(movement.getIntermediateX(), movement.getIntermediateY());
        Node finalNode = board.getNode(movement.getDestinationX(), movement.getDestinationY());
        if (!isNull(origin) && !isNull(intermediate) && !isNull(finalNode)) {
            Match match = new Match();
            match.setPlayer(user);
            match.setMovement(createMovement(movement));
            finalNode.setValue(1);
            origin.setValue(null);
            intermediate.setValue(null);
            matchRepository.save(match);
        }

        return board;
    }

    public List<String> getBoardHistory(Long id) {
        List<Match> movements = matchRepository.findByPlayerId(id);
        return movements.stream().map(Match::getMovement).toList();
    }

    private Board createBoard() {
        Board newBoard = new Board(5);
        for (int i = 0; i < 5; i++) {
            List<Node> level = createLevel(newBoard, i);
            newBoard.addLevel(level);
        }
        return newBoard;
    }

    private List<Node> createLevel(Board newBoard, int i) {
        List<Node> level = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            Node node = createNode(i, j);
            level.add(node);
            linkNodes(newBoard, i, j, node);
        }
        return level;
    }

    private Node createNode(int i, int j) {
        if (i == 0 && j == 0) {
            return new Node(null, i, j);
        } else {
            return new Node(1, i, j);
        }
    }

    private void linkNodes(Board newBoard, int i, int j, Node node) {
        if (i > 0) {
            if (j < newBoard.getNodes().get(i - 1).size()) {
                node.link(newBoard.getNode(i - 1, j));
            }
            if (j > 0) {
                node.link(newBoard.getNode(i - 1, j - 1));
            }
        }
    }

    private Player saveMatchInitializationAndReturnPlayer(String name) {
        Player player = new Player();
        player.setName(name);
        playerRepository.save(player);
        Match movement = new Match();
        movement.setPlayer(player);
        movement.setMovement("new Match");
        matchRepository.save(movement);
        return player;
    }

    private boolean isNull(Node node) {
        return node == null;
    }

    private String createMovement(Movement movement) {
        return String.format("Move from (%d, %d) to (%d, %d) via (%d, %d)", 
        movement.getOriginX(), movement.getOriginY(), 
        movement.getDestinationX(), movement.getDestinationY(), 
        movement.getIntermediateX(), movement.getIntermediateY());
    }
}
