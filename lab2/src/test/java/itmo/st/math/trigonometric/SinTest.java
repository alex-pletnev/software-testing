package itmo.st.math.trigonometric;

import itmo.st.exceptions.PrecisionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SinTest {

    private static final double PRECISION = 1E-6;

    private final double[][] validArguments = {
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
            {10.0, -0.544021},
            {50.0, -0.262375},
            {1000000.0, -0.349993}
    };

    @Test
    public void sinApplyWithValidArguments() {
        Sin sin = new Sin();
        for (var arguments : validArguments) {
            double x = arguments[0], expected = arguments[1];
            double real = sin.apply(x, PRECISION);

            assertEquals(expected, real, PRECISION);
        }
    }

    private final Double[][] invalidArguments = {
            {0.0, 0.0},
            {0.0, 10E-17},
            {0.0, -1.0},
    };

    @Test
    public void sinApplyWithInvalidArguments() {
        Sin sin = new Sin();
        for (var arguments : invalidArguments) {
            double x = arguments[0], precision = arguments[1];

            assertThrows(PrecisionException.class, () -> sin.apply(x, precision));
        }
    }

}
