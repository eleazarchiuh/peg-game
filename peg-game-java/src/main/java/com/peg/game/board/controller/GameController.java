package com.peg.game.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peg.game.board.converter.BoardConverter;
import com.peg.game.board.converter.MovementRequestConverter;
import com.peg.game.board.dto.BoardDTO;
import com.peg.game.board.dto.MovementRequestDTO;
import com.peg.game.board.dto.NewMatchDTO;
import com.peg.game.board.model.Board;
import com.peg.game.board.model.Movement;
import com.peg.game.board.services.BoardService;

@RestController()
@RequestMapping("/peg-game")
@CrossOrigin
public class GameController {
    private BoardService boardService;
    private BoardConverter boardConverter;
    private MovementRequestConverter movementRequestConverter;

    @Autowired
    public GameController(BoardService boardService, BoardConverter boardConverter, MovementRequestConverter movementRequestConverter) {
        this.boardService = boardService;
        this.boardConverter = boardConverter;
        this.movementRequestConverter = movementRequestConverter;
    }

    @PostMapping("/match")
    public BoardDTO match(@RequestBody NewMatchDTO boardDTO) {
        Board board = boardService.createNew(boardDTO.getPlayer());
        return boardConverter.toDTO(board);
    }

    @GetMapping("/board/{playerId}")
    public BoardDTO getBoard(@PathVariable Long playerId) {
        Board board = boardService.getBoardByPlayerId(playerId);
        return boardConverter.toDTO(board);
    }

    @PostMapping("/move/{playerId}")
    public BoardDTO movement(@RequestBody MovementRequestDTO movementRequest, @PathVariable Long playerId) {
        Movement movement = movementRequestConverter.toModel(movementRequest);
        Board board = boardService.movement(playerId, movement);
        return boardConverter.toDTO(board);
    }

    @GetMapping("/board/history/{playerId}")
    public List<String> getBoardHistory(@PathVariable Long playerId) {
         return boardService.getBoardHistory(playerId);
    }
}