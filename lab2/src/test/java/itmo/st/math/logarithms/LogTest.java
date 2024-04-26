package itmo.st.math.logarithms;

import itmo.st.exceptions.PrecisionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LogTest {

    private static final double PRECISION = 1E-6;

    private Ln mLn;
    private final double[][] lnValues = {
            {1.0, 0.0},
            {2.0, 0.6931471805599453},
            {3.0, 1.0986122886681096},
            {0.5, -0.6931471805599453},
            {1.5, 0.4054651081081644},
            {2.5, 0.9162907318741551},
            {3.5, 1.252762968495368},
            {5.0, 1.6094379124341003},
            {10.0, 2.302585092994046},
    };

    @Before
    public void setUp() {
        mLn = mock(Ln.class);
        for (var value : lnValues) {
            when(mLn.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] log2validArguments = {
            {1.0, 0.0},
            {2.0, 1.0},
            {3.0, 1.584962500721156},
            {0.5, -1.0},
            {1.5, 0.5849625007211562},
            {2.5, 1.3219280948873624},
            {3.5, 1.8073549220576042},
    };

    private final double[][] log5validArguments = {
            {1.0, 0.0},
            {2.0, 0.43067655807339306},
            {3.0, 0.6826061944859853},
            {0.5, -0.43067655807339306},
            {1.5, 0.25192963641259225},
            {2.5, 0.569323441926607},
            {3.5, 0.7783853970487746},
    };

    private final double[][] log10validArguments = {
            {1.0, 0.0},
            {2.0, 0.3010299956639812},
            {3.0, 0.47712125471966244},
            {0.5, -0.3010299956639812},
            {1.5, 0.17609125905568124},
            {2.5, 0.3979400086720376},
            {3.5, 0.5440680443502757},
    };

    @Test
    public void log2ApplyWithValidArgumentsWithLnMock() {
        Log log = new Log(2, mLn);
        for (var value : log2validArguments) {
            double x = value[0], expected = value[1];

            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void log5ApplyWithValidArgumentsWithLnMock() {
        Log log = new Log(5, mLn);
        for (var value : log5validArguments) {
            double x = value[0], expected = value[1];

            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void log10ApplyWithValidArgumentsWithLnMock() {
        Log log = new Log(10, mLn);
        for (var value : log10validArguments) {
            double x = value[0], expected = value[1];

            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    private final double[][] invalidArguments1 = {
            {0.0, PRECISION},
            {-1.0, PRECISION},
            {-10.0, PRECISION},

    };


    @Test
    public void logApplyWithInvalidArguments() {
        Log log = new Log(2);
        for (var arguments : invalidArguments1) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(ArithmeticException.class, () -> log.apply(x, precision));
        }

    }

    @Test
    public void log2ApplyWithValidArguments() {
        Log log = new Log(2);
        for (var value : log2validArguments) {
            double x = value[0], expected = value[1];
            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void log5ApplyWithValidArguments() {
        Log log = new Log(5);
        for (var value : log5validArguments) {
            double x = value[0], expected = value[1];
            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void log10ApplyWithValidArguments() {
        Log log = new Log(10);
        for (var value : log10validArguments) {
            double x = value[0], expected = value[1];
            var real = log.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    @Test
    public void logConstr() {
        assertNotNull(new Log());
        assertNotNull(new Log(10.0));
        assertNotNull(new Log(0.1));
        assertThrows(NullPointerException.class, () -> new Log(null));
        assertThrows(ArithmeticException.class, () -> new Log(0.0));
        assertThrows(ArithmeticException.class, () -> new Log(1.0));
        assertThrows(ArithmeticException.class, () -> new Log(-1.1));

    }




}
