package chess.model;

import chess.model.board.Position;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    public String side;
    public String isKingDead;
    public String pieceInfo;

    public void setSide(Side side) {
        this.side = side == Side.WHITE ? "w" : "b";
    }

    public void setIsKingDead(boolean isKingDead) {
        this.isKingDead = isKingDead ? "t" : "f";
    }

    public void setPositions(List<Position> positions) {
        List<String> infos = new ArrayList<>();
        for (Position position : positions) {
            infos.add(new Gson().toJson(position.createPieceInfo()));
        }
        pieceInfo = new Gson().toJson(infos);
    }
}
