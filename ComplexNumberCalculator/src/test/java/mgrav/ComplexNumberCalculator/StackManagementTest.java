package mgrav.ComplexNumberCalculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mgrav.ComplexNumberCalculator.ComplexNumber;
import mgrav.ComplexNumberCalculator.StackManagement;

import static org.junit.jupiter.api.Assertions.*;

class StackManagementTest {
    StackManagement stack= new StackManagement();
    ComplexNumber x1;
    ComplexNumber x2;
    ComplexNumber x3;

    @BeforeEach
    void setUp() {
        x1= new ComplexNumber(1, 1);
        x2= new ComplexNumber(2, 2);
        x3= new ComplexNumber(3, 3);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void push() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        System.out.println("Testing stack push...");
        assertEquals(new ComplexNumber(3, 3), stack.peek());
    }

    @Test
    void pop() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        ComplexNumber popped= stack.pop();
        System.out.println("Testing stack pop...");
        assertEquals(new ComplexNumber(3, 3), popped);
        assertEquals(new ComplexNumber(2, 2), stack.peek());
    }

    @Test
    void drop() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.drop();
        System.out.println("Testing stack drop...");
        assertEquals(new ComplexNumber(2, 2), stack.peek());
    }

    @Test
    void clear() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.clear();
        System.out.println("Testing stack clear...");
        assertTrue(stack.isEmpty());
    }

    @Test
    void duplicate() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.duplicate();
        System.out.println("Testing stack duplication...");
        assertEquals(new ComplexNumber(3, 3), stack.peek());
        stack.drop();
        assertEquals(new ComplexNumber(3, 3), stack.peek());
    }

    @Test
    void over() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.over();
        System.out.println("Testing stack duplication of second-to-last element...");
        assertEquals(new ComplexNumber(2, 2), stack.peek());
        stack.drop();
        assertEquals(new ComplexNumber(3, 3), stack.peek());
        stack.drop();
        assertEquals(new ComplexNumber(2, 2), stack.peek());
    }

    @Test
    void swap() {
        stack.push(x1);
        stack.push(x2);
        stack.push(x3);
        stack.swap();
        System.out.println("Testing stack swapping of the last two elements...");
        assertEquals(new ComplexNumber(2, 2), stack.peek());
        stack.drop();
        assertEquals(new ComplexNumber(3, 3), stack.peek());
        stack.drop();
        assertEquals(new ComplexNumber(1, 1), stack.peek());
    }
}