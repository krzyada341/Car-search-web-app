/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Class representing model of the database
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class Database {
    
    /**
     * ArrayList of the Car objects
     */
    private ArrayList<Car> cars = new ArrayList<>();
    
     /**
     * Database's filename
     */    
    private String filename;
    
     /**
     * Method which reads data from file and saves objects into array
     *
     * @throws FileNotFoundException when file is not found
     */
    public void setDatabase() throws FileNotFoundException {
        
        File file = new File(filename);
        Scanner scFile = new Scanner(file);
        String type, category, price;
        while (scFile.hasNext()) {
            
            if(scFile.hasNext())
                type = scFile.next();
            else type = null;
            
            if(scFile.hasNext())
                category = scFile.next();         
            else category = null;
            
            if(scFile.hasNext())
                price = scFile.next();  
            else price = null;
            
            if(type != null && category != null && price != null)
                this.cars.add(new Car(type, category.charAt(0), Double.parseDouble(price)));
        }
    }
    
     /**
     * Returns the database
     *
     * @return ArrayList with Car objects
     */
    public ArrayList<Car> getDatabase() {
        return cars;
    }
    
     /**
     * Returns the filename
     *
     * @return filename name of database
     */
    public String getFilename() {
        return filename;
    }
    
     /**
     * Setting the filename for database
     *
     * @param filename name for database
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
     /**
     * Method which set price for specified car group
     *
     * @param group is the car's price group
     * @param strPrice is the car's price string from user
     * @throws ParametersException when user didn't pass any arguments
     */
    public void setGroupPrice(char group, String strPrice) throws ParametersException {
        group = Character.toUpperCase(group);         
        if(NumberUtils.isNumber(strPrice)) {
            double price = Double.parseDouble(strPrice);
            if(group == 'A' || group == 'B' || group == 'C') {
                for(Car n : cars) {
                    if(n != null) {
                        if(n.getCategory() == group)
                        n.setPrice(price);
                    }
                }
            } else 
                throw new ParametersException("Car's price group are only A, B or C!");              
        } else 
            throw new ParametersException("Car's price is not a number!");               
    }
}
