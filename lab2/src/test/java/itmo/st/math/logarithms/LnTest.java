package itmo.st.math.logarithms;

import itmo.st.exceptions.PrecisionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LnTest {

    private static final double PRECISION = 1E-6;

    private final double[][] validArguments = {
            {1.0, 0.0},
            {2.0, 0.6931471805599453},
            {3.0, 1.0986122886681096},
            {0.5, -0.6931471805599453},
            {1.5, 0.4054651081081644},
            {2.5, 0.9162907318741551},
            {3.5, 1.252762968495368},
    };

    @Test
    public void lnApplyWithValidArguments() {
        Ln ln = new Ln();
        for (var arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];
            double real = ln.apply(x, PRECISION);
            assertEquals(expected, real, PRECISION);
        }
    }

    private final double[][] invalidArguments1 = {
            {0.0, PRECISION},
            {-1.0, PRECISION},
            {-10.0, PRECISION},

    };

    private final double[][] invalidArguments2 = {
            {1.0, 0.0},
            {1.0, 10E-17},
            {1.0, -1.0},
    };

    @Test
    public void lnApplyWithInvalidArguments() {
        Ln ln = new Ln();
        for (var arguments : invalidArguments1) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(ArithmeticException.class, () -> ln.apply(x, precision));
        }
        for (var arguments : invalidArguments2) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(PrecisionException.class, () -> ln.apply(x, precision));
        }
    }
}
