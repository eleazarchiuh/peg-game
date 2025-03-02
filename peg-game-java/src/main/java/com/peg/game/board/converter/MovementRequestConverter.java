package com.peg.game.board.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.peg.game.board.dto.MovementRequestDTO;
import com.peg.game.board.model.Movement;

@Component
public class MovementRequestConverter {
    public Movement toModel(MovementRequestDTO requestDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(requestDTO, Movement.class);
    }
}
