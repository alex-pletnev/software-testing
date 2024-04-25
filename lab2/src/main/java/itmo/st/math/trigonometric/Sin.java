package itmo.st.math.trigonometric;

import itmo.st.math.MathFunction;
import itmo.st.math.simple.Factorial;
import itmo.st.math.util.Convectors;
import itmo.st.math.util.Validators;

public class Sin implements MathFunction {

    @Override
    public Double apply(Double x, Double precision) {
        Validators.validateDoubleArgument(x);
        Validators.validPrecision(precision);

        double convX = Convectors.convArgForSin(x);
        Factorial factorial = new Factorial();
        double term = convX;
        double sum = 0;
        int n = 1;
        boolean add = true;

        while (Math.abs(term) >= precision) {
            if (add) {
                sum += term;
            } else {
                sum -= term;
            }
            n += 2;
            term = Math.pow(convX, n) / factorial.apply((double) n);
            add = !add;
        }

        return sum;

    }

}
