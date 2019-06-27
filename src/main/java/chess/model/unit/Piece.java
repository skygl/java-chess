package chess.model.unit;

import chess.model.InvalidElementException;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class Piece {
    private static final Map<UnitType, Function<Side, Piece>> createMap = new HashMap<>();
    private static final String NO_PIECE_SYMBOL_ERROR_MSG = "해당하는 SYMBOL의 PIECE가 없습니다.";
    private static final int DEFAULT_SYMBOL_LENGTH = 2;

    static {
        createMap.put(UnitType.KING, King::new);
        createMap.put(UnitType.QUEEN, Queen::new);
        createMap.put(UnitType.BISHOP, Bishop::new);
        createMap.put(UnitType.KNIGHT, Knight::new);
        createMap.put(UnitType.PAWN, Pawn::new);
        createMap.put(UnitType.ROOK, Rook::new);
    }

    private UnitType type;
    private Side side;

    Piece(UnitType type, Side side) {
        this.type = type;
        this.side = side;
    }

    public static Piece createPiece(String pieceSymbol) {
        if (pieceSymbol.length() != DEFAULT_SYMBOL_LENGTH)
            throw new InvalidElementException(NO_PIECE_SYMBOL_ERROR_MSG);
        return createMap.get(UnitType.findTypeWith(pieceSymbol.substring(1, 2)))
                .apply(Side.findSide(pieceSymbol.substring(0, 1)));
    }

    public abstract List<SquareNavigator> findSquareNavigators(Square beginSquare);

    Side getSide() {
        return side;
    }

    public boolean isSameSide(Piece piece) {
        if (piece == null)
            return false;
        return this.side == piece.side;
    }

    public boolean isSameSide(Side side) {
        return this.side == side;
    }

    public String toSymbolString() {
        return side == Side.WHITE ? type.getSymbol().toLowerCase() : type.getSymbol();
    }

    public boolean isPieceOf(UnitType unitType) {
        return this.type == unitType;
    }

    public double getScore() {
        return type.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return type == piece.type &&
                side == piece.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, side);
    }

    @Override
    public String toString() {
        return side.getSymbol() + type.getSymbol();
    }
}