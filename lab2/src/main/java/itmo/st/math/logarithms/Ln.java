package itmo.st.math.logarithms;

import itmo.st.math.MathFunction;
import itmo.st.math.util.Validators;

public class Ln implements MathFunction {

    @Override
    public Double apply(Double x, Double precision) {
        Validators.validateDoubleArgument(x);
        Validators.validPrecision(precision);
        if (x <= 0 || x - 1 == -1) {
            throw new ArithmeticException("x must be greater than 0");
        }

        if (x == 1.0) {
            return 0.0;
        }

        boolean invert = x >= 2;
        if (invert) {
            x = 1.0 / x;
        }

        double z = x - 1;
        double term = z;
        double sum = 0;
        int k = 1;

        while (Math.abs(term) > precision) {
            sum += term / k;
            k++;
            term *= -z;
        }

        return invert ? -sum : sum;
    }
}
