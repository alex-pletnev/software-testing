package itmo.st.exceptions;

public class PrecisionException extends ArithmeticException {

    public PrecisionException() {
        super("Precision must be greater than 10E-16 and less than 1");
    }
}
