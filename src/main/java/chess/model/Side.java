package chess.model;

public enum Side {
    WHITE("w"), BLACK("b");

    private String side;

    Side(String side) {
        this.side = side;
    }

    public static Side findSide(String side) {
        System.out.println(side);
        return side.equals(BLACK.side) ? BLACK : WHITE;
    }

    public Side changeSide() {
        return this.equals(WHITE) ? BLACK : WHITE;
    }

    public String getSide() {
        return side;
    }
}
