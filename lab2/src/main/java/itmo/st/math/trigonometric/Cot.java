package itmo.st.math.trigonometric;

import itmo.st.math.MathFunction;

public class Cot implements MathFunction {

    private final Sin sin;
    private final Cos cos;

    public Cot() {
        this(new Sin(), new Cos());
    }

    public Cot(Sin sin, Cos cos) {
        if (sin == null || cos == null) {
            throw new NullPointerException("Sin and Cos cannot be null");
        }

        this.sin = sin;
        this.cos = cos;
    }

    @Override
    public Double apply(Double x, Double precision) {
        if (x % Math.PI == 0) {
            throw new ArithmeticException("cot is inf in x%pi zero");
        }

        return cos.apply(x, precision) / sin.apply(x, precision);
    }
}
