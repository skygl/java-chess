package chess.model;

import chess.model.board.Square;

public class Move {
    private final Square preSquare;
    private final Square postSquare;

    public Move(Square preSquare, Square postSquare) {
        this.postSquare = postSquare;
        this.preSquare = preSquare;
    }

    public Square getPreSquare() {
        return preSquare;
    }

    public Square getPostSquare() {
        return postSquare;
    }
}
