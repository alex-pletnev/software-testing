package itmo.st;

import itmo.st.io.CsvSaver;
import itmo.st.math.MathFunction;
import itmo.st.math.approx.MathFunctionApproximator;
import itmo.st.math.logarithms.Ln;
import itmo.st.math.systems.SystemOfInequalities;
import itmo.st.math.trigonometric.Cot;

import java.util.Date;

/**
 * Hello world!
 */

public class App {
    public static void main(String[] args) {
//        Cos cos = new Cos();
//        System.out.println("cos(1.5707963) = " + cos.apply(1.5707963, 10E-6));
//
        MathFunction mSin = (x, pr) ->  1 / Math.sin(x);

        SystemOfInequalities systemOfInequalities = new SystemOfInequalities();

        var approxResult = MathFunctionApproximator.approximate(systemOfInequalities, 10E-8, -3.5, 4.0, 0.5, true);
        CsvSaver.saveApproxResultToCsv(approxResult);
        //"Math_Log5-" + new Date() + ".csv"
    }
}
