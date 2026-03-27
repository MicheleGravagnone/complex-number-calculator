package mgrav.ComplexNumberCalculator.exceptions;

public class InvalidOperationException extends  RuntimeException{
    public InvalidOperationException(){
        super();
    }
    public InvalidOperationException(String errorMessage){
        super(errorMessage);
    }
}
