package mgrav.ComplexNumberCalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mgrav.ComplexNumberCalculator.ComplexNumber;
import mgrav.ComplexNumberCalculator.StackManagement;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorControllerTest {
    Map<Character, ComplexNumber> variableMap= new HashMap<>();
    StackManagement stack= new StackManagement();
    char a;
    char b;
    char c;
    ComplexNumber x1;
    ComplexNumber x2;
    ComplexNumber x3;

    @BeforeEach
    void setUp() {
        a='a';
        b='b';
        c='c';
        x1= new ComplexNumber(1, 1);
        x2= new ComplexNumber(2, 2);
        x3= new ComplexNumber(3, 3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void variableOperations() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        System.out.println("Testing save stack top to a variable...");
        variableMap.put(a, stack.pop());
        assertEquals(new ComplexNumber(3, 3), variableMap.get(a));
        assertEquals(new ComplexNumber(2, 2), stack.peek());
        System.out.println("Testing add stack top to a variable...");
        ComplexNumber add= variableMap.get(a).add(stack.pop());
        variableMap.replace(a, add);
        assertEquals(new ComplexNumber(5, 5), variableMap.get(a));
        assertEquals(new ComplexNumber(1, 1), stack.peek());
        System.out.println("Testing add stack top to a variable...");
        ComplexNumber sub= variableMap.get(a).sub(stack.pop());
        variableMap.replace(a, sub);
        assertEquals(new ComplexNumber(4, 4), variableMap.get(a));
        assertTrue(stack.isEmpty());
        System.out.println("Testing add save variable to stack top...");
        stack.push(variableMap.get(a));
        assertEquals(new ComplexNumber(4, 4), stack.peek());
    }
}