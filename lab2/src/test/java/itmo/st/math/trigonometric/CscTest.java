package itmo.st.math.trigonometric;

import itmo.st.exceptions.PrecisionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class CscTest {

    private static final double PRECISION = 1E-6;

    private Sin mSin;
    private final double[][] sinValues = {
            {-2.0, -0.9092974268256817},
            {0.0, 0.0},
            {-1.0, -0.8414709848078965},
            {1.0, 0.8414709848078965},
            {0.5, 0.479425538604203},
            {1.5, 0.9974949866040543},
            {-1.5, -0.9974949866040543},
            {-0.5, -0.479425538604203},
    };

    @Before
    public void setUp() {
        mSin = mock(Sin.class);
        for (var value : sinValues) {
            when(mSin.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            {-2.0, -1.0997501702946164},
            {-1.0, -1.1883951057781212},
            {1.0, 1.1883951057781212},
            {0.5, 2.085829642933488},
            {1.5, 1.002511304246725},
            {-1.5, -1.002511304246725},
            {-0.5, -2.085829642933488},
    };

    @Test
    public void cscApplyWithValidArgumentsWithSinMock() {
        Csc csc = new Csc(mSin);
        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = csc.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(mSin).apply(x, PRECISION);
        }
    }

    private final double[][] invalidArguments1 = {
            {0.0, PRECISION},
            {Math.PI, PRECISION},
            {-Math.PI * 5, PRECISION},

    };

    private final double[][] invalidArguments2 = {
            {1.0, 0.0},
            {1.0, 10E-17},
            {1.0, -1.0},
    };

    @Test
    public void cscApplyWithInvalidArguments() {
        Csc csc = new Csc();
        for (var arguments : invalidArguments1) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(ArithmeticException.class, () -> csc.apply(x, precision));
        }
        for (var arguments : invalidArguments2) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(PrecisionException.class, () -> csc.apply(x, precision));
        }
    }

    @Test
    public void cscApplyWithValidArguments() {
        Csc csc = new Csc();
        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = csc.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }


}
