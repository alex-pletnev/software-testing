package itmo.st.math.trigonometric;

import itmo.st.math.MathFunction;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Cos implements MathFunction {

    private final Sin sin;

    public Cos() {
        this(new Sin());
    }

    public Cos(Sin sin) {
        if (sin == null) {
            throw new NullPointerException("sin is null");
        }
        this.sin = sin;
    }

    @Override
    public Double apply(Double x, Double precision) {
        return sqrt(1 - pow(sin.apply(x, precision), 2));
    }
}
