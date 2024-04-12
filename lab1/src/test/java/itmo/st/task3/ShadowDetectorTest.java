package itmo.st.task3;

import itmo.st.task3.model.Sunshade;
import itmo.st.task3.model.Table;
import itmo.st.task3.model.TableShape;
import itmo.st.task3.util.ShadowDetector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShadowDetectorTest {

    @Test
    void testCircleTableCompletelyInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 10);
        Table table = new Table(0, 0, 5, TableShape.CIRCLE);
        assertTrue(ShadowDetector.isTableInShadow(sunshade, table));
    }

    @Test
    void testCircleTablePartiallyInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 10);
        Table table = new Table(8, 0, 3, TableShape.CIRCLE);
        assertFalse(ShadowDetector.isTableInShadow(sunshade, table));
    }

    @Test
    void testCircleTableNotInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 5);
        Table table = new Table(10, 10, 3, TableShape.CIRCLE);
        assertFalse(ShadowDetector.isTableInShadow(sunshade, table));
    }

    @Test
    void testSquareTableCompletelyInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 10);
        Table table = new Table(0, 0, 2, TableShape.SQUARE);
        assertTrue(ShadowDetector.isTableInShadow(sunshade, table));
    }

    @Test
    void testSquareTablePartiallyInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 10);
        Table table = new Table(6, 0, 3, TableShape.SQUARE);
        assertFalse(ShadowDetector.isTableInShadow(sunshade, table));
    }

    @Test
    void testSquareTableNotInShadow() {
        Sunshade sunshade = new Sunshade(0, 0, 5);
        Table table = new Table(10, 10, 3, TableShape.SQUARE);
        assertFalse(ShadowDetector.isTableInShadow(sunshade, table));
    }
}
