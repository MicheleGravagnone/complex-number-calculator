package mgrav.ComplexNumberCalculator;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mgrav.ComplexNumberCalculator.exceptions.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class StackManagement {
    private Stack<ComplexNumber> stack;

    public StackManagement(){
        this.stack= new Stack<>();
    }

    /*This method passes every element of the stack into an observable array list
    * and displays them from the bottom to the top
    */
    public void updateList(ObservableList<ComplexNumber> stackList) {
        stackList.setAll(stack);
        FXCollections.reverse(stackList);
    }

    public void push(ComplexNumber complexNumber){ stack.push(complexNumber); }
    public ComplexNumber pop() throws EmptyStackException {
        if(stack.empty())
            throw new EmptyStackException();
        return stack.pop();
    }
    public int lengthStack(){
        return stack.size();
    }
    public boolean isEmpty(){
        return stack.empty();
    }

    /*This method removes a stack element without assigning it to a variable*/
    public void drop()throws EmptyStackException{
        if(stack.empty())
            throw new EmptyStackException("The stack is empty");
        stack.pop();
    }

    /*This method removes every element input into the stack*/
    public void clear()throws EmptyStackException{
        if(stack.empty())
            throw new EmptyStackException("The stack is empty");
        stack.clear();
    }
    /*This method duplicates the stack top*/
    public void duplicate()throws EmptyStackException{
        if(stack.empty())
            throw new EmptyStackException("The stack is empty");
        stack.push(stack.peek());
    }
    /*This method pushes a copy of the second-to-last element onto the top*/
    public void over() throws NotEnoughNumbersException{
        if(stack.size()<2)
            throw new NotEnoughNumbersException("There aren't enough numbers");
        ComplexNumber top= stack.pop();
        ComplexNumber dup= stack.peek();
        stack.push(top);
        stack.push(dup);
    }

    /*This method swaps the last two elements*/
    public void swap() throws NotEnoughNumbersException{
        if(stack.size()<2)
            throw new NotEnoughNumbersException("There aren't enough numbers");
        ComplexNumber top= stack.pop();
        ComplexNumber dup= stack.pop();
        stack.push(top);
        stack.push(dup);
    }

    public ComplexNumber peek() {
        return stack.peek();
    }
}
