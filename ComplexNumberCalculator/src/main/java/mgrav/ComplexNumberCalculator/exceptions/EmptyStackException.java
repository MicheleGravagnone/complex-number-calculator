package mgrav.ComplexNumberCalculator.exceptions;

public class EmptyStackException extends RuntimeException {
    public EmptyStackException(){
        super();
    }
    public EmptyStackException(String errorMessage){
        super(errorMessage);
    }
}
