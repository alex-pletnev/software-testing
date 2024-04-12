package itmo.st.task1;

public class Asin {

    private Asin() {
    }

    private static final double ACCURACY = 1.0E-8;

    public static double asin(double x) {
        if (x < -1 || x > 1) {
            return Double.NaN;
        }

        double term = x;
        double sum = x;
        double numerator = x;
        int denominator;

        int n = 1;
        while (Math.abs(term) > ACCURACY) {
            numerator *= x * x;
            denominator = 2 * n * (2 * n + 1);
            term = numerator / denominator;
            sum += term;
            n++;
        }
        return sum;
    }
}

