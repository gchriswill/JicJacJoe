package edu.bu.met.CS622.JicJacJoe.Resources;

/**
 * A custom exception class type for throwing an exception when the game encounters
 * an error while saving a game session.
 * Uses Inheritance and Polymorphism
 */
public class SaveSessionErrorException extends Exception { // Inheritance

    // The constructor of the class.
    public SaveSessionErrorException(String message) {
        super(message); // Polymorphism
    }
}
