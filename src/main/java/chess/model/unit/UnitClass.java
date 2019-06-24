package chess.model.unit;

import chess.model.Side;

import java.util.Arrays;
import java.util.function.Function;

public enum UnitClass {
    KING("King", "K", King::new),
    QUEEN("Queen", "Q", Queen::new),
    ROOK("Rook", "R", Rook::new),
    BISHOP("Bishop", "B", Bishop::new),
    KNIGHT("Knight", "N", Knight::new),
    PAWN("PawnMove", "P", Pawn::new);

    private final String name;
    private final String symbol;
    private final Function<Side, Piece> createMethod;

    UnitClass(final String name, final String symbol, final Function<Side, Piece> createMethod) {
        this.name = name;
        this.symbol = symbol;
        this.createMethod = createMethod;
    }

    public String getName() {
        return name;
    }

    public static Piece createPiece(String side, String symbol) {
        return Arrays.stream(UnitClass.values()).filter(c -> symbol.equals(c.symbol)).findAny().get().createMethod.apply(Side.findSide(side));
    }

    public String getSymbol() {
        return symbol;
    }
}
