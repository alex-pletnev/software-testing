package itmo.st.math;

import java.util.function.BiFunction;

public interface MathFunction extends BiFunction<Double, Double, Double> {

    Double apply(Double x, Double precision);

    default Double apply(Double x) {
        return apply(x, 10E-8);
    }
}
