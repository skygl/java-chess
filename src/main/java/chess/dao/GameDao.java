package chess.dao;

import chess.model.Move;
import chess.model.PieceInfo;
import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.UnitClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class GameDao {
    private Connection con;

    public GameDao(Connection con) {
        this.con = con;
    }

    public Board loadBoard() throws SQLException {
        String searchQuery = "SELECT * FROM piece";
        PreparedStatement pstmt = con.prepareStatement(searchQuery);
        ResultSet rs = pstmt.executeQuery();

        Board board = Board.makeEmptyBoard();
        if (!rs.next()) {
            return insertNewBoard();
        }
        do {
            board.setPiece(new Square(rs.getString("position"))
                    , UnitClass.createPiece(rs.getString("side"), rs.getString("unitType")));
        } while (rs.next());

        return board;
    }

    public Side loadSide() throws SQLException {
        String searchQuery = "SELECT * FROM chessgame";
        PreparedStatement pstmt = con.prepareStatement(searchQuery);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) {
            return Side.WHITE;
        }
        return Side.findSide(rs.getString("side"));
    }

    private Board insertNewBoard() throws SQLException {
        Board board = Board.makeInitialBoard();
        insertGame();
        for (Position position : board.getAllPositions()) {
            System.out.println(position.getSquare().toString());
        }
        insertPiece(board.getAllPositions().stream()
                .map(Position::createPieceInfo)
                .collect(Collectors.toList()));
        return board;
    }

    private void insertGame() throws SQLException {
        String insertQuery = "INSERT INTO chessgame (side) values ('W')";
        PreparedStatement pstmt = con.prepareStatement(insertQuery);
        pstmt.executeUpdate();
    }

    private void insertPiece(List<PieceInfo> infos) throws SQLException {
        String insertQuery = "INSERT INTO piece (unitType, side, position) values (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(insertQuery);
        for (PieceInfo info : infos) {
            pstmt.setString(1, info.type);
            pstmt.setString(2, info.side);
            pstmt.setString(3, info.position);
            pstmt.executeUpdate();
            pstmt.clearParameters();
        }
    }

    public void changeSide(Side side) throws SQLException {
        String updateQuery = "UPDATE chessgame SET side = ? WHERE side = ?";
        PreparedStatement pstmt = con.prepareStatement(updateQuery);
        pstmt.setString(1, side.getSide());
        ;
        pstmt.setString(2, side.changeSide().getSide());
        pstmt.executeUpdate();
    }

    public void updateChanges(Move move) throws SQLException {
        String deleteQuery = "DELETE FROM piece WHERE position = ?";
        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        pstmt.setString(1, move.getPostSquare().toString());
        System.out.println("DELETE");
        pstmt.executeUpdate();

        String updateQuery = "UPDATE piece SET position = ? WHERE position = ?";
        pstmt = con.prepareStatement(updateQuery);
        pstmt.setString(1, move.getPostSquare().toString());
        pstmt.setString(2, move.getPreSquare().toString());
        System.out.println("UPDATE");
        pstmt.executeUpdate();
    }
}
