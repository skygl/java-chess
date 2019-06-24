package chess.controller;

import chess.model.MoveResult;
import chess.model.board.Square;
import com.google.gson.Gson;
import spark.Route;

public class ChessGameController {
    private static ChessService service;
    public static Route serveIndexPage = (request, response) -> {
        response.redirect("/chessgame.html");
        return null;
    };

    public static Route move = (request, response) -> {
        response.type("application/json");
        MoveResult moveResult = new MoveResult();
        try {
            Square preSquare = new Square(request.queryMap("source").value());
            Square postSquare = new Square(request.queryMap("target").value());
            moveResult.setSuccess(service.move(preSquare, postSquare));
            moveResult.setKingDead(service.kingCheck());
        } catch (Exception e) {
            moveResult.setSuccess(false);
            response.status(500);
        }
        return new Gson().toJson(moveResult);
    };

    public static Route initialize = (request, response) -> {
        response.type("application/json");
        try {
            return new Gson().toJson(service.initialize());
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson(e.getMessage());
        }
    };

    static {
        service = new ChessService();
    }
}
