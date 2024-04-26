package itmo.st.math.trigonometric;

import itmo.st.exceptions.PrecisionException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class CosTest {

    private static final double PRECISION = 1E-6;

    private Sin mSin;
    private final double[][] sinValues = {
            {-1.0471975, -0.866025},
            {-0.5235987, -0.5},
            {0.0, 0.0},
            {0.5235987, 0.5},
            {1.0471975, 0.866025},
            {1.5707963, 1.0},
            {2.0943951, 0.866025},
            {2.6179938, 0.5},
            {Math.PI, 0.0},
            {5.0, -0.958924},
            {10.0, -0.544021}
    };

    @Before
    public void setUp() {
        mSin = mock(Sin.class);
        for (var value : sinValues) {
            when(mSin.apply(value[0], PRECISION * PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            {-1.0471975, 0.5},
            {-0.5235987, 0.866025},
            {0.0, 1.0},
            {0.5235987, 0.866025},
            {1.0471975, 0.5},
            {1.5707963, 0.0},
            {2.0943951, -0.5},
            {2.6179938, -0.866025},
            {Math.PI, -1.0},
            {5.0, 0.283663},
            {10.0, -0.839072}
    };

    @Test
    public void cosApplyWithValidArgumentsWithSinMock() {
        Cos cos = new Cos(mSin);
        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = cos.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
            verify(mSin).apply(x, PRECISION * PRECISION);
        }
    }

    private final Double[][] invalidArguments = {
            {0.0, 0.0},
            {0.0, 10E-17},
            {0.0, -1.0},
    };

    @Test
    public void cosApplyWithInvalidArguments() {
        Cos cos = new Cos();
        for (var arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(PrecisionException.class, () -> cos.apply(x, precision));
        }
    }

    @Test
    public void cosApplyWithValidArguments() {
        Cos cos = new Cos();
        for (var value : validArguments) {
            double x = value[0], expected = value[1];
            var real = cos.apply(x, PRECISION);
            if (real.isNaN()) System.out.println("x=" + x + " expected=" + expected);
            assertEquals(expected, real, PRECISION);
        }
    }




}
