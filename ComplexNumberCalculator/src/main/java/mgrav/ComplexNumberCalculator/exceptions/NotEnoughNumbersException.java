package mgrav.ComplexNumberCalculator.exceptions;

public class NotEnoughNumbersException extends RuntimeException{
    public NotEnoughNumbersException(){
        super();
    }
    public NotEnoughNumbersException(String errorMessage){
        super(errorMessage);
    }
}
