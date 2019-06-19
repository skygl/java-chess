package chess.model;

import java.util.HashMap;
import java.util.Map;

public enum Row {
    _1("1", 0),
    _2("2", 1),
    _3("3", 2),
    _4("4", 3),
    _5("5", 4),
    _6("6", 5),
    _7("7", 6),
    _8("8", 7);

    private static final String NO_SUCH_VALUE = "존재하지 않는 Row 값입니다.";
    private static final Map<Integer, Row> map = initializeMap();

    private String name;
    private int rowIndex;

    Row(final String name, final int rowIndex) {
        this.name = name;
        this.rowIndex = rowIndex;
    }

    private static Map<Integer, Row> initializeMap() {
        final Map<Integer, Row> map = new HashMap<>();
        for (Row value : values()) {
            map.put(value.rowIndex, value);
        }
        return map;
    }

    //    public int getIndexByName(final String name) {
    //        for (Row value : Row.values()) {
    //            if (value.name.equals(name)) {
    //                return value.rowIndex;
    //            }
    //        }
    //        throw new IllegalArgumentException(NO_SUCH_VALUE);
    //    }
    //
    //    public String getNameByIndex(final int rowIndex) {
    //        for (Row value : Row.values()) {
    //            if (value.rowIndex == rowIndex) {
    //                return value.name;
    //            }
    //        }
    //        throw new IllegalArgumentException(NO_SUCH_VALUE);
    //    }
    //
    public static Row getByIndex(final int rowIndex) {
        final Row value = map.get(rowIndex);
        if (value == null) {
            throw new IllegalArgumentException(NO_SUCH_VALUE);
        }
        return value;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public boolean isFirst() {
        return this == Row._1;
    }

    public boolean isLast() {
        return this == Row._8;
    }

    @Override
    public String toString() {
        return "Row " + this.name;
    }
}
