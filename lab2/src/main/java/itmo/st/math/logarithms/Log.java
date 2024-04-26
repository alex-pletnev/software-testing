package itmo.st.math.logarithms;

import itmo.st.math.MathFunction;

public class Log implements MathFunction {

    private final double base;
    private final Ln ln;

    public Log() {
        this(Math.E, new Ln());
    }

    public Log(Ln ln) {
        this(Math.E, ln);
    }

    public Log(double base) {
        this(base, new Ln());
    }

    public Log(double base, Ln ln) {
        if (base <= 0) {
            throw new ArithmeticException("Base must be greater than zero");
        }
        if (base == 1) {
            throw new ArithmeticException("Base cannot be one, as logarithm is undefined for base 1");
        }
        if (ln == null) {
            throw new NullPointerException("ln is null");
        }

        this.base = base;
        this.ln = ln;
    }

    @Override
    public Double apply(Double x, Double precision) {
        if ((x >= 1.0 - (precision * 10.0)) && (x <= 1.0 + (precision * 10.0))) {
            return 0.0;
        }

        if ((base >= (x + precision * 10.0)) && (base <= (x + precision * 10.0))) {
            return 1.0;
        }
        if (base == Math.E) return ln.apply(x, precision);
        return ln.apply(x, precision) / ln.apply(base, precision);
    }
}
