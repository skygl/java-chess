package chess.model.unit;

import chess.model.Column;
import chess.model.Direction;
import chess.model.Square;
import chess.model.SquareNavigator;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    Pawn(Side side) {
        super(UnitType.PAWN, side);
    }

    @Override
    public List<SquareNavigator> findSquareNavigators(Square beginSquare) {
        return Direction.valueOfPawnMove(getSide()).stream()
                .map(direction -> new SquareNavigator(direction, beginSquare, isFirstMove(beginSquare) ? 2 : 1))
                .collect(Collectors.toList());
    }

    private boolean isFirstMove(Square beginSquare) {
        return (getSide() == Side.BLACK && beginSquare.isAtColumn(Column._7)) ||
                (getSide() == Side.WHITE && beginSquare.isAtColumn(Column._2));
    }
}