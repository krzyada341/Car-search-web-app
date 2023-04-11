/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.test;

import pl.polsl.model.Car;
import org.junit.jupiter.api.*;

/**
 * Test class for testing Car model class.
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class CarTest {
    private Car car;
    
     /**
     * Set up new car object for test
     */
    @BeforeEach
    public void setUp() {
        car = new Car("Sport", 'A', 40000);
    }
}
