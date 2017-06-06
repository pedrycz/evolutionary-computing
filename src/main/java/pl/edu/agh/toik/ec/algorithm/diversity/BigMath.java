package pl.edu.agh.toik.ec.algorithm.diversity;

import org.apfloat.Apcomplex;
import org.apfloat.ApcomplexMath;
import org.apfloat.Apfloat;

import java.math.BigDecimal;

class BigMath {


    static BigDecimal power(BigDecimal a, BigDecimal b) {
        return convert(ApcomplexMath.pow(convert(a), convert(b)));
    }

    static BigDecimal log(BigDecimal bigDecimal) {
        return convert(ApcomplexMath.log(convert(bigDecimal)));
    }

    static BigDecimal exp(BigDecimal bigDecimal) {
        return convert(ApcomplexMath.exp(convert(bigDecimal)));
    }

    static BigDecimal sqrt(BigDecimal bigDecimal) {
        return convert(ApcomplexMath.sqrt(convert(bigDecimal)));
    }

    private static Apcomplex convert(BigDecimal bigDecimal) {
        return new Apcomplex(new Apfloat(bigDecimal));
    }

    private static BigDecimal convert(Apcomplex apcomplex) {
        return new BigDecimal(String.format("%s", apcomplex.real()));
    }
}
