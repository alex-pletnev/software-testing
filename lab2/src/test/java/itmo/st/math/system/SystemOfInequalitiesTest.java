package itmo.st.math.system;

import itmo.st.exceptions.PrecisionException;
import itmo.st.math.logarithms.Ln;
import itmo.st.math.logarithms.Log;
import itmo.st.math.systems.SystemOfInequalities;
import itmo.st.math.trigonometric.Cot;
import itmo.st.math.trigonometric.Csc;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SystemOfInequalitiesTest {

    private static final double PRECISION = 1E-4;

    private Cot mCot;
    private Csc mCsc;
    private Ln mLn;
    private Log mLog2;
    private Log mLog5;
    private Log mLog10;


    private final double[][] cotValues = {
            {-2.0, 0.45765755436028577},
            {-1.0, -0.6420926159343308},
            {-3.5, -2.669616484968866},
            {-2.5, 1.3386481283041514},
            {-3.0, 7.015252551434534},
            {-1.5, -0.07091484430265245},
            {-0.5, -1.830487721712452},
    };

    private final double[][] cscValues = {
            {-2.0, -1.0997501702946164},
            {-1.0, -1.1883951057781212},
            {-3.5, 2.850763437540464},
            {-2.5, -1.67092154555868},
            {-3.0, -7.086167395737187},
            {-1.5, -1.002511304246725},
            {-0.5, -2.085829642933488},
    };

    private final double[][] lnValues = {
            {1.0, 0.0},
            {2.0, 0.6931471805599453},
            {3.0, 1.0986122886681096},
            {0.5, -0.6931471805599453},
            {1.5, 0.4054651081081644},
            {2.5, 0.9162907318741551},
            {3.5, 1.252762968495368},
    };

    private final double[][] log2Values = {
            {1.0, 0.0},
            {2.0, 1.0},
            {3.0, 1.584962500721156},
            {0.5, -1.0},
            {1.5, 0.5849625007211562},
            {2.5, 1.3219280948873624},
            {3.5, 1.8073549220576042},
    };

    private final double[][] log5Values = {
            {1.0, 0.0},
            {2.0, 0.43067655807339306},
            {3.0, 0.6826061944859853},
            {0.5, -0.43067655807339306},
            {1.5, 0.25192963641259225},
            {2.5, 0.569323441926607},
            {3.5, 0.7783853970487746},
    };

    private final double[][] log10Values = {
            {1.0, 0.0},
            {2.0, 0.3010299956639812},
            {3.0, 0.47712125471966244},
            {0.5, -0.3010299956639812},
            {1.5, 0.17609125905568124},
            {2.5, 0.3979400086720376},
            {3.5, 0.5440680443502757},
    };

    @Before
    public void setUp() {
        mCot = mock(Cot.class);
        for (var value : cotValues) {
            when(mCot.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
        mCsc = mock(Csc.class);
        for (var value : cscValues) {
            when(mCsc.apply(value[0], PRECISION)).thenReturn(value[1]);
        }

        mLn = mock(Ln.class);
        for (var value : lnValues) {
            when(mLn.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
        mLog2 = mock(Log.class);
        for (var value : log2Values) {
            when(mLog2.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
        mLog5 = mock(Log.class);
        for (var value : log5Values) {
            when(mLog5.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
        mLog10 = mock(Log.class);
        for (var value : log10Values) {
            when(mLog10.apply(value[0], PRECISION)).thenReturn(value[1]);
        }
    }

    private final double[][] validArguments = {
            {-2.0, -1.6312987952961013},
//            {0.0,csc is inf in x%pi zero},
            {-1.0, -1.1785123374307656},
            {2.0, 0.4824907888439146},
            {3.0, 1.2160957938260735},
            {0.5, 1.0961964372883528},
            {-3.5, 3.67791583795714E15},
            {1.5, 0.21261484959593022},
            {-2.5, 48.79368610475966},
            {2.5, 0.8350888878639614},
            {3.5, 1.603422541726867},
            {-3.0, 2.7932230354116734E30},
            {-1.5, -1.022598198640668},
            {-0.5, 4.4571346679287565E8},
    };


    @Test
    public void systemOfInequalitiesApplyWithMocks() {
        SystemOfInequalities systemOfInequalities = new SystemOfInequalities(
                mCsc, mCot, mLn, mLog2, mLog5, mLog10
        );

        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = systemOfInequalities.apply(x, PRECISION);

            boolean isEquals = real >= expected - PRECISION || real <= expected + PRECISION;

            assertTrue(isEquals);

        }
    }

    private final double[][] invalidArguments1 = {
            {0.0, PRECISION},
    };

    private final double[][] invalidArguments2 = {
            {1.0, 0.0},
            {1.0, 10E-17},
            {1.0, -1.0},
    };

    @Test
    public void systemOfInequalitiesApplyWithInvalidArguments() {
        Cot cot = new Cot();
        for (var arguments : invalidArguments1) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(ArithmeticException.class, () -> cot.apply(x, precision));
        }
        for (var arguments : invalidArguments2) {
            double x = arguments[0], precision = arguments[1];
            assertThrows(PrecisionException.class, () -> cot.apply(x, precision));
        }
    }

    @Test
    public void systemOfInequalitiesApply() {
        SystemOfInequalities systemOfInequalities = new SystemOfInequalities();

        for (var value : validArguments) {
            double x = value[0], expected = value[1];

            var real = systemOfInequalities.apply(x, PRECISION);

            boolean isEquals = real >= expected - PRECISION || real <= expected + PRECISION;

            assertTrue(isEquals);

        }
    }

}

