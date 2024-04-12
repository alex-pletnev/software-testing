package itmo.st.task3.util;

import itmo.st.task3.model.Sunshade;
import itmo.st.task3.model.Table;

public class ShadowDetector {

    private ShadowDetector() {
    }

    public static boolean isTableInShadow(Sunshade sunshade, Table table) {
        return switch (table.shape()) {
            case CIRCLE -> isCircleTableInShadow(sunshade, table);
            case SQUARE -> isSquareTableInShadow(sunshade, table);
        };
    }

    private static boolean isCircleTableInShadow(Sunshade sunshade, Table table) {
        double distance = Math.sqrt(Math.pow(table.x() - sunshade.x(), 2) + Math.pow(table.y() - sunshade.y(), 2));
        return distance + table.range() <= sunshade.range();
    }

    private static boolean isSquareTableInShadow(Sunshade sunshade, Table table) {
        double squareDiagonal = table.range() * Math.sqrt(2);
        double distance = Math.sqrt(Math.pow(table.x() - sunshade.x(), 2) + Math.pow(table.y() - sunshade.y(), 2));
        return distance + squareDiagonal <= sunshade.range();
    }
}
