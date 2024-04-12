package itmo.st.task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

 class AsinTest {

     private static final double ACCURACY = 1.0E-8;

    @Test
    void testAsinForBoundaryValues() {
        assertEquals(Double.NaN, Asin.asin(-1.1));
        assertEquals(Double.NaN, Asin.asin(1.1));
        assertNotEquals(Double.NaN, Asin.asin(-1));
        assertNotEquals(Double.NaN, Asin.asin(1));
    }

    @Test
    void testAsinForSpecialValues() {
        assertEquals(0, Asin.asin(0), ACCURACY);
    }

    @Test
    void testAsinForTypicalValues() {
        double result = Asin.asin(0.5);
        assertFalse(Double.isNaN(result));
        assertFalse(Double.isInfinite(result));
    }

    @Test
    void testAsinNearZero() {
        double result = Asin.asin(1.0E-13);
        assertFalse(Double.isNaN(result));
        assertTrue(result > 0);
    }
}
