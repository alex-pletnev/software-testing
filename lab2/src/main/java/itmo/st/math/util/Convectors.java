package itmo.st.math.util;

import static java.lang.Math.PI;

public class Convectors {

    private Convectors() {
    }

    public static double convArgForSin(double x) {
        while (x > PI) x -= 2 * PI;
        while (x < -PI) x += 2 * PI;
        return x;
    }
}
