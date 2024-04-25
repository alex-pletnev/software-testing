package itmo.st.math.trigonometric;

import itmo.st.math.MathFunction;

public class Csc implements MathFunction {

    private final Sin sin;

    public Csc() {
        this(new Sin());
    }

    public Csc(Sin sin) {
        if (sin == null) {
            throw new NullPointerException("sin is null");
        }
        this.sin = sin;
    }

    @Override
    public Double apply(Double x, Double precision) {
        if (x % Math.PI == 0) {
            throw new ArithmeticException("csc is inf in x%pi zero");
        }

        return 1 / sin.apply(x, precision);
    }
}
