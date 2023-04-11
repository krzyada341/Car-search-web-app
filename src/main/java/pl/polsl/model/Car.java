/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;

/**
 * A containter for car data. It consists of type, price category and
 * price.
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class Car {
    
    /**
     * Car's type
     */
    private String type;
    
    /**
     * Car's price category
     */
    private char category;
    
    /**
     * Car's price
     */
    private double price;

     /**
     * Constructor with 3 arguments, creates Car object with given parametres
     *
     * @param type is type of the car
     * @param category is price category of the car
     * @param price is the price of the car
     */
    public Car(String type, char category, double price) {
         this.type = type;
         this.category = category;
         this.price = price;
    }
    
     /**
     * Returns the type of the car
     *
     * @return Type of the car
     */
    public String getType() {
        return type;
    }
    
     /**
     * Returns the price category of the car
     *
     * @return price category of the car
     */
    public char getCategory() {
        return category;
    }
     /**
     * Returns the price of the car
     *
     * @return Price of the car
     */
    public double getPrice() {
        return price;
    }
    
     /**
     * Sets the type of the car
     *
     * @param type the car's type to set
     */
    public void setType(String type) {
        this.type = type;
    }
    
     /**
     * Sets the price category of the car
     *
     * @param category the car's price category to set
     */
    public void setCategory(char category) {
        this.category = category;
    }
    
     /**
     * Sets the price of the car
     *
     * @param price the car's price to set
     */
    public void setPrice(double price){
        this.price = price;
    }
}
