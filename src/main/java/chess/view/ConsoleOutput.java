package chess.view;

import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.*;

public class ConsoleOutput {
    private static final Map<UnitClass, String> PIECE_SYMBOL = new HashMap<>();
    private static final String EMPTY_SQUARE;
    private static final String NEW_LINE;
    private static final String MESSAGE_START;

    static {
        NEW_LINE = "\n";
        EMPTY_SQUARE = ".";
        MESSAGE_START =
                "> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n" +
                "> 게임 이동 : move source위치 target위치 (예시 : move b2 b3)";
    }

    static {
        PIECE_SYMBOL.put(UnitClass.KING, "K");
        PIECE_SYMBOL.put(UnitClass.QUEEN, "Q");
        PIECE_SYMBOL.put(UnitClass.ROOK, "R");
        PIECE_SYMBOL.put(UnitClass.BISHOP, "B");
        PIECE_SYMBOL.put(UnitClass.KNIGHT, "N");
        PIECE_SYMBOL.put(UnitClass.PAWN, "P");
    }

    private static String getPieceSymbol(final Board board, final Square square) {
        String symbol = EMPTY_SQUARE;
        try {
            final Piece piece = board.getPiece(square);
            symbol = getSymbolBySide(piece);
        } catch (Exception e) {
            // pass
        }
        return symbol;
    }

    private static String getSymbolBySide(final Piece piece) {
        String symbol = PIECE_SYMBOL.get(piece.getUnitClass());
        if (piece.getSide().equals(Side.WHITE)) {
            symbol = symbol.toLowerCase();
        }
        return symbol;
    }

    public static String getBoardString(final Board board) {
        final StringBuilder result = new StringBuilder();
        final List<StringBuilder> sbList = new ArrayList<>();
        for (Row row : Row.values()) {
            sbList.add(boardRow(board, row));
        }
        Collections.reverse(sbList);
        for (StringBuilder sb : sbList) {
            result.append(sb);
            result.append(NEW_LINE);
        }
        return result.toString();
    }

    private static StringBuilder boardRow(final Board board, final Row row) {
        Square square;
        StringBuilder sb = new StringBuilder();
        for (Column column : Column.values()) {
            square = new Square(column, row);
            sb.append(getPieceSymbol(board, square));
        }
        return sb;
    }

    public static void startMessage() {
        System.out.println(MESSAGE_START);
    }
}
