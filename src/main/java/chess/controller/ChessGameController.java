package chess.controller;

import chess.model.board.Square;
import com.google.gson.Gson;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class ChessGameController {
    public static Route serveIndexPage = (request, response) -> {
        response.redirect("/chessgame.html");
        return null;
    };
    private static ChessService service;
    public static Route move = (request, response) -> {
        response.type("application/json");
        Map<String, Object> model = new HashMap<>();
        try {
            model.put("result", service.canMove(new Square(request.queryMap("source").value()), new Square(request.queryMap("target").value())));
            return new Gson().toJson(model);
        } catch (Exception e) {
            model.put("result", false);
            response.status(500);
            return new Gson().toJson(model);
        }
    };

    static {
        service = new ChessService();
    }
}
