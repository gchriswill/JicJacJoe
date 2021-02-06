package edu.bu.met.CS622.JicJacJoe.Resources;

/**
 * A custom exception class type for throwing an exception when the user inputs is invalid.
 * Uses Inheritance and Polymorphism
 */
public class IllegalUserInputException extends Exception { // Inheritance

    // The constructor of the class.
    public IllegalUserInputException(String message) {
        super(message); // Polymorphism
    }
}
