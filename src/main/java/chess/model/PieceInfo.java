package chess.model;

import chess.model.board.Square;

public class PieceInfo {
    public String type;
    public String side;
    public String position;

    public PieceInfo() {
    }

    public PieceInfo(final String type, final String side, final String position) {
        this.type = type;
        this.side = side;
        this.position = position;
    }

    public void setPosition(Square square) {
        this.position = square.toString();
    }

    public void setSide(Side side) {
        this.side = (side == Side.WHITE) ? "w" : "b";
    }

    public void setType(String type) {
        this.type = type;
    }

    public Square getSquare() {
        return new Square(position);
    }
}
