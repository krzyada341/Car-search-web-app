/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;
import pl.polsl.model.Database;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pl.polsl.model.Car;
import pl.polsl.model.ParametersException;

/**
 * Test class for testing Database model class.
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class DatabaseTest {

     /**
     * Interface for managing Database
     */
    private Database database;

    /**
     * Constructor of the DatabaseTest.
     */
    public DatabaseTest() {
    }

    /**
     * Set up new Database object for testing
     */
    @BeforeEach
    public void setUp() {
        database = new Database();
    }

    /**
     * Test of setDatabase method of model class Database. Setting the filename
     * for database in model and checks if filename will be set correctly. After
     * that, test checks if database will be set from this file correctly
     *
     * @param filename Database's filename
     * @throws FileNotFoundException when filename is not found
     */
    @ParameterizedTest
    @ValueSource(strings = {"baza.txt", " ", "baza", "", "emptyFile.txt", "fileWithBadArguments.txt", "plik.png", "plik.txt"})
    public void testSetDatabase(String filename) throws FileNotFoundException {
        String type, category, price;
        database.setFilename(filename);
        File f = new File(filename);
        ArrayList<Car> cars = new ArrayList<>();
        assertEquals(filename, database.getFilename(), "Filename hasn't been set correctly");
        if (!f.exists()) {
            assertThrows(FileNotFoundException.class, () -> database.setDatabase(), "The exception hasn't been thrown, where it was expected");
        } else {
            database.setDatabase();
            Scanner scFile = new Scanner(f);
            while (scFile.hasNext()) {

                if (scFile.hasNext()) {
                    type = scFile.next();
                } else {
                    type = null;
                }

                if (scFile.hasNext()) {
                    category = scFile.next();
                } else {
                    category = null;
                }

                if (scFile.hasNext()) {
                    price = scFile.next();
                } else {
                    price = null;
                }

                if (type != null && category != null && price != null) {
                    cars.add(new Car(type, category.charAt(0), Double.parseDouble(price)));
                }
            }
            for (int i = 0; i < cars.size(); i++) {
                assertEquals(cars.get(i).getType(), database.getDatabase().get(i).getType(), "Type hasn't been set correctly");
                assertEquals(cars.get(i).getCategory(), database.getDatabase().get(i).getCategory(), "Category hasn't been set correctly");
                assertEquals(cars.get(i).getPrice(), database.getDatabase().get(i).getPrice(), "Price hasn't been set correctly");
            }
        }
    }

    /**
     * Test of setGroupPrice method of model class Database. Setting the
     * filename for database in model and checks if price for group will be set
     * correctly.
     *
     * @param group is the car's price group
     * @param strPrice is the car's price string from user
     * @throws FileNotFoundException when filename is not found
     * @throws ParametersException when selected parameter is not passed
     */
    @ParameterizedTest
    @CsvSource({"a,555", "A,123", "B,5555", "B,fsad", "' ',123", "1,asd", "G,123", "' ',"})
    public void testSetGroupPrice(char group, String strPrice) throws FileNotFoundException, ParametersException {
        database.setFilename("baza.txt");
        database.setDatabase();
        final char charGroup = group;
        if (!NumberUtils.isNumber(strPrice)) {
            assertThrows(ParametersException.class, () -> database.setGroupPrice(charGroup, strPrice), "The exception hasn't been thrown, where it was expected");
        } else {
            group = Character.toUpperCase(group);
            if (group == 'A' || group == 'B' || group == 'C') {
                database.setGroupPrice(group, strPrice);
                double price = Double.parseDouble(strPrice);
                for (Car n : database.getDatabase()) {
                    if (n.getCategory() == group) {
                        assertEquals(price, n.getPrice(), "Price hasn't been set correctly");
                    }
                }
            }
        }
    }
}
