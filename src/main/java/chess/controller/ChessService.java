package chess.controller;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.rule.Rule;

public class ChessService {
    private Board board = Board.makeInitialBoard();

    public boolean canMove(Square source, Square target) {
        return Rule.isValidMove(board, source, target);
    }
}
