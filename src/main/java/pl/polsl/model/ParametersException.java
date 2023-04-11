/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * Exception class expection when user didn't pass any arguments
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class ParametersException extends Exception {
    
    /**
     * Get the information about error
     *
     * @param message the message
     */
    public ParametersException(String message) {
        super(message);
    }
    
}
