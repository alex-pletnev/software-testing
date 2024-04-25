package itmo.st.math.systems;

import itmo.st.math.MathFunction;
import itmo.st.math.logarithms.Ln;
import itmo.st.math.logarithms.Log;
import itmo.st.math.trigonometric.Cot;
import itmo.st.math.trigonometric.Csc;
import itmo.st.math.util.Validators;

public class SystemOfInequalities implements MathFunction {

    private final Csc csc;
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log5;
    private final Log log10;

    public SystemOfInequalities() {
        this(new Csc(), new Cot(), new Ln(), new Log(2), new Log(5), new Log(10));
    }

    public SystemOfInequalities(Csc csc, Cot cot, Ln ln, Log log2, Log log5, Log log10) {
        this.csc = csc;
        this.cot = cot;
        this.ln = ln;
        this.log2 = log2;
        this.log5 = log5;
        this.log10 = log10;
    }

    @Override
    public Double apply(Double x, Double precision) {
        Validators.validateDoubleArgument(x);
        if (x <= 0) {
            var step1 = csc.apply(x, precision) + cot.apply(x, precision);
            var step2 = step1 - cot.apply(x, precision);
            var step3 = Math.pow(cot.apply(x, precision), 2);
            var step4 = Math.pow(step3, 2);
            var step5 = step2 + step4;
            var step6 = Math.pow(step5, 3);
            return Math.pow(step6, 3);
            //(((((csc(x) + cot(x)) - cot(x)) + ((cot(x) ^ 2) ^ 2)) ^ 3) ^ 3)
        } else {
            double stepA = log10.apply(x, precision) / log2.apply(x, precision);
            double stepB = stepA * ln.apply(x, precision);
            double stepC = Math.pow(stepB, 2);

            double stepD = ln.apply(x, precision) - log2.apply(x, precision);
            double stepE = log5.apply(x, precision) * (log2.apply(x, precision) + log10.apply(x, precision));
            double stepF = stepD + stepE;

            double stepG = log5.apply(x, precision) / log2.apply(x, precision);
            double stepH = Math.pow(stepG, 2);

            return stepC + stepF + stepH;
            //(((((log10(x) / log2(x)) * ln(x)) ^ 2) + ((ln(x) - log2(x)) + (log5(x) * (log2(x) + log10(x))))) + ((log5(x) / log2(x)) ^ 2))
        }
    }
}
