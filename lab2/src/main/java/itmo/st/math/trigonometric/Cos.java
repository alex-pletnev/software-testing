package itmo.st.math.trigonometric;

import itmo.st.math.MathFunction;
import itmo.st.math.util.Convectors;

import static java.lang.Math.*;

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
        var cos = sqrt(max(1 - pow(sin.apply(x, precision * precision), 2), 0));
        Double convX = Convectors.convArgForSin(x);
        return (convX > -PI / 2 && convX < PI / 2) ? cos : -cos;
    }
}
