package itmo.st.math.approx;

import itmo.st.math.MathFunction;
import itmo.st.math.util.Validators;

import java.util.HashMap;
import java.util.Map;

public class MathFunctionApproximator {

    private MathFunctionApproximator() {

    }

    public static ApproxResult approximate(MathFunction function, Double from, Double to, Double step) {
        return approximate(function, 10E-8, from, to, step, false);
    }

    public static ApproxResult approximate(MathFunction function, Double from, Double to, Double step, boolean debug) {
        return approximate(function, 10E-8, from, to, step, debug);
    }

    public static ApproxResult approximate(MathFunction function, Double precision, Double from, Double to, Double step) {
        return approximate(function, precision, from, to, step, false);
    }

    public static ApproxResult approximate(MathFunction function, Double precision, Double from, Double to, Double step, boolean debug) {
        Validators.validateDoubleArgument(from);
        Validators.validateDoubleArgument(to);
        Validators.validateDoubleArgument(step);
        if (from >= to || step <= 0) {
            throw new IllegalArgumentException("'from' must be lower than to 'to' and step must be greater than 0");
        }

        String name = function.getClass().getSimpleName();
        Map<String, String> results = new HashMap<>();
        var x = from;
        while (x < to) {
            try {
                results.put(x.toString(), function.apply(x, precision).toString());
            } catch (ArithmeticException e) {
                if (debug) {
                    results.put(x.toString(), e.getMessage());
                } else {
                    results.put(x.toString(), "");
                }
            }
            x += step;
        }
        return new ApproxResult(name, results);
    }
}
