package itmo.st.math.util;

import itmo.st.exceptions.PrecisionException;

public class Validators {

    private Validators() {
    }

    public static void validPrecision(double precision) {
        if (!(precision >= 10E-16 && precision < 1)) {
            throw new PrecisionException();
        }
    }

    public static void validateDoubleArgument(Double x) {
        if (x == null || Double.isNaN(x) || Double.isInfinite(x)) {
            throw new ArithmeticException("invalid argument");
        }
    }


}
