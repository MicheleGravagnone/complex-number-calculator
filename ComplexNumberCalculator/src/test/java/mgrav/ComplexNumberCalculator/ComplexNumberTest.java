package mgrav.ComplexNumberCalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mgrav.ComplexNumberCalculator.ComplexNumber;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    ComplexNumber c1;
    ComplexNumber c2;
    ComplexNumber r1;
    ComplexNumber r2;
    ComplexNumber i1;
    ComplexNumber i2;

    @BeforeEach
    void setUp() {
        c1= new ComplexNumber(1, 2);
        c2= new ComplexNumber(2, 3);
        r1= new ComplexNumber(5, 0);
        r2= new ComplexNumber(4, 0);
        i1= new ComplexNumber(0, 3);
        i2= new ComplexNumber(0, 7);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        System.out.println("Testing addiction between complex numbers...");
        assertEquals(new ComplexNumber(3, 5), c1.add(c2));
        System.out.println("Testing addiction between two real numbers...");
        assertEquals(new ComplexNumber(9, 0), r1.add(r2));
        System.out.println("Testing addiction between two complex numbers with the real part that equals zero...");
        assertEquals(new ComplexNumber(0, 10), i1.add(i2));
        System.out.println("Testing addiction between a complex number and a real number...");
        assertEquals(new ComplexNumber(6, 2), c1.add(r1));
        System.out.println("Testing addiction between a real number and a complex number with the real part that equals zero...");
        assertEquals(new ComplexNumber(4, 3), r2.add(i1));
        System.out.println("Testing addiction between two complex numbers, with one of them having the real part that equals zero...");
        assertEquals(new ComplexNumber(2, 10), c2.add(i2));
    }

    @Test
    void sub() {
        System.out.println("Testing subtraction between complex numbers...");
        assertEquals(new ComplexNumber(-1, -1), c1.sub(c2));
        System.out.println("Testing subtraction between two real numbers...");
        assertEquals(new ComplexNumber(1, 0), r1.sub(r2));
        System.out.println("Testing subtraction between two complex numbers with the real part that equals zero...");
        assertEquals(new ComplexNumber(0, -4), i1.sub(i2));
        System.out.println("Testing subtraction between a complex number and a real number...");
        assertEquals(new ComplexNumber(-4, 2), c1.sub(r1));
        System.out.println("Testing subtraction between a real number and a complex number with the real part that equals zero...");
        assertEquals(new ComplexNumber(4, -3), r2.sub(i1));
        System.out.println("Testing subtraction between two complex numbers, with one of them having the real part that equals zero...");
        assertEquals(new ComplexNumber(2, -4), c2.sub(i2));
    }

    @Test
    void molt() {
        System.out.println("Testing multiplication between complex numbers...");
        assertEquals(new ComplexNumber(-4, 7), c1.molt(c2));
        System.out.println("Testing multiplication between two real numbers...");
        assertEquals(new ComplexNumber(20, 0), r1.molt(r2));
        System.out.println("Testing multiplication between two complex numbers with the real part that equals zero...");
        assertEquals(new ComplexNumber(-21, 0), i1.molt(i2));
        System.out.println("Testing multiplication between a complex number and a real number...");
        assertEquals(new ComplexNumber(5, 10), c1.molt(r1));
        System.out.println("Testing multiplication between a real number and a complex number with the real part that equals zero...");
        assertEquals(new ComplexNumber(0, 12), r2.molt(i1));
        System.out.println("Testing multiplication between two complex numbers, with one of them having the real part that equals zero...");
        assertEquals(new ComplexNumber(-21, 14), c2.molt(i2));
    }

    @Test
    void div() {
        double delta = 0.01;
        System.out.println("Testing division between complex numbers...");
        assertComplexEquals(new ComplexNumber(-0.31, -0.08), c1.div(c2), delta);
        System.out.println("Testing division between two real numbers...");
        assertComplexEquals(new ComplexNumber(1.25, 0), r1.div(r2), delta);
        System.out.println("Testing division between two complex numbers with the real part that equals zero...");
        assertComplexEquals(new ComplexNumber(-0.43, 0), i1.div(i2), delta);
        System.out.println("Testing division between a complex number and a real number...");
        assertComplexEquals(new ComplexNumber(0.20, -0.40), c1.div(r1), delta);
        System.out.println("Testing division between a real number and a complex number with the real part that equals zero...");
        assertComplexEquals(new ComplexNumber(0, 1.33), r2.div(i1), delta);
        System.out.println("Testing division between two complex numbers, with one of them having the real part that equals zero...");
        assertComplexEquals(new ComplexNumber(-0.43, 0.29), c2.div(i2), delta);
    }

    @Test
    void invSign() {
        System.out.println("Testing sign inversion for a complex number...");
        assertEquals(new ComplexNumber(-1, -2), c1.invSign());
        System.out.println("Testing sign inversion for a real number...");
        assertEquals(new ComplexNumber(-5, 0), r1.invSign());
        System.out.println("Testing sign inversion for a complex number with the real part that equals 0...");
        assertEquals(new ComplexNumber(0, -3), i1.invSign());
    }

    @Test
    void sqrt() {
        double delta= 0.01;
        System.out.println("Testing sign inversion for a complex number...");
        assertComplexEquals(new ComplexNumber(1.27, 0.79), c1.sqrt(), delta);
        System.out.println("Testing sign inversion for a real number...");
        assertComplexEquals(new ComplexNumber(2.24, 0), r1.sqrt(), delta);
        System.out.println("Testing sign inversion for a complex number with the real part that equals 0...");
        assertComplexEquals(new ComplexNumber(1.22, 1.22), i1.sqrt(), delta);
    }

    // Helper method for division and square root
    private void assertComplexEquals(ComplexNumber expected, ComplexNumber actual, double delta) {
        assertEquals(expected.getReal(), actual.getReal(), delta);
        assertEquals(expected.getImag(), actual.getImag(), delta);
    }
}