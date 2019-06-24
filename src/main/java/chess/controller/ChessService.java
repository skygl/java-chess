package chess.controller;

import chess.ConnectionFactory;
import chess.dao.GameDao;
import chess.model.Move;
import chess.model.Play;
import chess.model.Side;
import chess.model.board.Square;
import chess.view.ConsoleOutput;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private static GameDao gameDao;

    static {
        Connection con = ConnectionFactory.connect();
        gameDao = new GameDao(con);
    }

    private Play play;

    private void createPlay() {
        try {
            play = new Play(gameDao.loadBoard(), gameDao.loadSide());
        } catch (SQLException e) {
            System.out.println("ERROR : " + e.getSQLState());
        }
    }

    public boolean move(Square source, Square target) {
        createPlay();
        try {
            play.movePieceAndTurnSide(source, target);
            gameDao.changeSide(play.getSide());
            gameDao.updateChanges(new Move(source, target));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean kingCheck() {
        createPlay();
        System.out.println(ConsoleOutput.getBoardString(play.getBoard()));
        return play.isKingDead(Side.WHITE) || play.isKingDead(Side.BLACK);
    }

    public Map<String, String> initialize() {
        Map<String, String> initialMap = new HashMap<>();
        createPlay();
        play.getAllPositions()
                .forEach(position -> initialMap.put(position.getSquare().toString()
                        , position.getPiece().toString()));
        return initialMap;
    }
}
