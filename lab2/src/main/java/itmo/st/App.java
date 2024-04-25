package itmo.st;

import itmo.st.io.CsvSaver;
import itmo.st.math.approx.MathFunctionApproximator;
import itmo.st.math.systems.SystemOfInequalities;

/**
 * Hello world!
 */

public class App {
    public static void main(String[] args) {
        SystemOfInequalities systemOfInequalities = new SystemOfInequalities();
        var approxResult = MathFunctionApproximator.approximate(systemOfInequalities, 10E-3, -0.3, 2.0, 0.1, true);
        CsvSaver.saveApproxResultToCsv(approxResult);
    }
}
