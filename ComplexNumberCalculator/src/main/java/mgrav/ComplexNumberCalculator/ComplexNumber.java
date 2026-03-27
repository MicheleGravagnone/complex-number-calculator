package mgrav.ComplexNumberCalculator;

import mgrav.ComplexNumberCalculator.exceptions.InvalidOperationException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ComplexNumber {
    private Double real;
    private Double imag;


    public ComplexNumber(double real, double imag){
        this.real=real;
        this.imag=imag;
    }

    public Double getReal() {
        return real;
    }

    public Double getImag() {
        return imag;
    }

    /*This method performs the addition*/
    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(real+other.real, imag+other.imag);
    }

    /*This method performs the subtraction*/
    public ComplexNumber sub(ComplexNumber other){
        return new ComplexNumber(real-other.real, imag-other.imag);
    }

    /*This method performs the multiplication*/
    public ComplexNumber molt(ComplexNumber other){
        return new ComplexNumber((real* other.real)-(imag* other.imag),(real*other.imag)+(imag* other.real));
    }

    /*This method performs the division*/
    public ComplexNumber div(ComplexNumber other) throws InvalidOperationException {
        //If the divisor is zero, the app throws an exception
        if(((other.real*other.real)+(other.imag* other.imag))==0)
            throw new InvalidOperationException();
        return new ComplexNumber(((real*other.real)-(imag*other.imag))/((other.real*other.real)+(other.imag* other.imag)), ((real*other.imag)-(imag*other.real))/((other.real*other.real)+(other.imag* other.imag)));
    }

    /*This method performs the sign inversion*/
    public ComplexNumber invSign(){
        if (imag==0)
            return new ComplexNumber(-real, 0);
        else if(real==0)
            return new ComplexNumber(0, -imag);
        else
            return new ComplexNumber(-real, -imag);
    }

    /*This method performs the square root*/
    public ComplexNumber sqrt(){
        double r=Math.sqrt((real*real)+(imag*imag));
        double angle=Math.atan2(imag,real);
        double resultReal=Math.sqrt(r)*(Math.cos(angle/2));
        double resultImag=Math.sqrt(r)*(Math.sin(angle/2));
        return new ComplexNumber(resultReal, resultImag);
    }

    /*This method compares two complex numbers*/
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ComplexNumber other = (ComplexNumber) obj;
        return Double.compare(real, other.real) == 0 &&
                Double.compare(imag, other.imag) == 0;
    }


    @Override
    public String toString() {
        return String.format("%.2f%+.2fj",real,imag);
    }

    /*This method is used to display the correct format into the complex number*/
    public String getFormattedComplexNumber() {
        return String.format("%.2f%+.2fj", real, imag);
    }


}
