package com.peg.game.board.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.peg.game.board.dto.BoardDTO;
import com.peg.game.board.model.Board;
import com.peg.game.board.model.Node;

@Component
public class BoardConverter {
    public BoardDTO toDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        List<List<Integer>> levels = new ArrayList<>();
        for(List<Node> level : board.getNodes()){
            List<Integer> levelValues = new ArrayList<>();
            for(Node node : level){
                levelValues.add(node.getValue());
            }
            levels.add(levelValues);
        }
        dto.setLevels(levels);
        dto.setPlayerId(board.getPlayerId());
        return dto;
    }
}
