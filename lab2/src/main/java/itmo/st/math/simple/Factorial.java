package itmo.st.math.simple;

import itmo.st.math.MathFunction;
import itmo.st.math.util.Validators;

public class Factorial implements MathFunction {

    @Override
    public Double apply(Double x, Double precision) {
        Validators.validateDoubleArgument(x);
        double result = 1;
        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }


}
