package itmo.st.math.trigonometric;

import itmo.st.exceptions.PrecisionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class CotTest {

    private static final double PRECISION = 1E-6;

    private Sin mSin;
    private Cos mCos;

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

    private final double[][] cosValues = {
            {-2.0, -0.4161468365471424},
            {0.0, 1.0},
            {-1.0, 0.5403023058681398},
            {1.0, 0.5403023058681398},
            {0.5, 0.8775825618903728},
            {1.5, 0.0707372016677029},
            {-1.5, 0.0707372016677029},
            {-0.5, 0.8775825618903728},
    };

    @Before
    public void setUp() {
        mSin = mock(Sin.class);
        for (var value : sinValues) {
            when(mSin.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
        mCos = mock(Cos.class);
        for (var value : cosValues) {
            when(mCos.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            {-2.0, 0.45765755436028577},
            {-1.0, -0.6420926159343308},
            {1.0, 0.6420926159343308},
            {0.5, 1.830487721712452},
            {1.5, 0.07091484430265245},
            {-1.5, -0.07091484430265245},
            {-0.5, -1.830487721712452},
    };

    @Test
    public void cotApplyWithValidArgumentsWithSinMockAndCosMock() {
        Cot cot = new Cot(mSin, mCos);
        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = cot.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(mSin).apply(x, PRECISION);
            verify(mCos).apply(x, PRECISION);
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
    public void cotApplyWithInvalidArguments() {
        Cot cot = new Cot();
        for (var arguments : invalidArguments1) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(ArithmeticException.class, () -> cot.apply(x, precision));
        }
        for (var arguments : invalidArguments2) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(PrecisionException.class, () -> cot.apply(x, precision));
        }
    }

    @Test
    public void cotApplyWithValidArguments() {
        Cot cot = new Cot();
        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = cot.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }



}
