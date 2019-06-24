package chess.model.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Column {
    A("A", 0),
    B("B", 1),
    C("C", 2),
    D("D", 3),
    E("E", 4),
    F("F", 5),
    G("G", 6),
    H("H", 7);

    private static final String NO_SUCH_VALUE = "존재하지 않는 Column 값입니다.";
    private static final Map<Integer, Column> map = initializeMap();

    private String name;
    private int columnIndex;

    Column(final String name, final int columnIndex) {
        this.name = name;
        this.columnIndex = columnIndex;
    }

    private static Map<Integer, Column> initializeMap() {
        final Map<Integer, Column> map = new HashMap<>();
        for (Column value : values()) {
            map.put(value.columnIndex, value);
        }
        return map;
    }

    public static Column findColumnByName(String name) {
        return Arrays.stream(Column.values()).filter(column -> column.name.equals(name)).findAny().get();
    }

    public static Column getByIndex(final int columnIndex) {
        final Column value = map.get(columnIndex);
        if (value == null) {
            throw new IllegalArgumentException(NO_SUCH_VALUE);
        }
        return value;
    }

    public String getColumnName() {
        return name;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public String toString() {
        return this.name.toLowerCase();
    }
}
