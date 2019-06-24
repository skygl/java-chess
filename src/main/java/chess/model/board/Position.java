package chess.model.board;

import chess.model.PieceInfo;
import chess.model.unit.Piece;

public class Position {
    private final Square square;
    private final Piece piece;

    Position(final Square square, final Piece piece) {
        this.square = square;
        this.piece = piece;
    }

    public Square getSquare() {
        return square;
    }

    public Piece getPiece() {
        return piece;
    }

    public PieceInfo createPieceInfo() {
        PieceInfo pieceInfo = new PieceInfo();
        pieceInfo.setPosition(square);
        pieceInfo.setSide(piece.getSide());
        pieceInfo.setType(piece.getUnitClass().getSymbol());
        return pieceInfo;
    }
}
