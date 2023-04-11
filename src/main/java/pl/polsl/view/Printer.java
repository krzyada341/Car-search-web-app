/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.io.PrintWriter;
import pl.polsl.model.Car;
import pl.polsl.model.Database;

/**
 * Prints html text in the browser
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
public class Printer {

    /**
     * Object for printing text
     *
     */
    private PrintWriter writer;

    /**
     * Default constructor
     *
     * @param writer is assigned to printWriter
     */
    public Printer(PrintWriter writer) {
        this.writer = writer;
    }

    /**
     * Method that prints whole database
     *
     * @param database is Cars database
     */
    public void displayCars(Database database) {

        writer.println("<html>\n<body>\n<h1>Cars: </h1>\n");
        writer.println("<table border=1><tr>" + "<th>Type</th>" + "<th>Category</th>" + "<th>Price</th>" + "</tr>");
        writer.println("<tr>");

        for (Car n : database.getDatabase()) {
            writer.println("<td>" + n.getType() + "</td>");
            writer.println("<td>" + n.getCategory() + "</td>");
            writer.println("<td>" + n.getPrice() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("</body>\n</html>");
    }

    /**
     * Method that prints cars with selected group price category from database
     *
     * @param database is Cars database
     * @param group is car's group price category
     */
    public void displayFromCategory(Database database, char group) {

        writer.println("<html>\n<body>\n<h1>Cars: </h1>\n");
        writer.println("<table border=1><tr>" + "<th>Type</th>" + "<th>Category</th>" + "<th>Price</th>" + "</tr>");
        writer.println("<tr>");

        for (Car n : database.getDatabase()) {
            if (n.getCategory() == group) {
                writer.println("<td>" + n.getType() + "</td>");
                writer.println("<td>" + n.getCategory() + "</td>");
                writer.println("<td>" + n.getPrice() + "</td>");
                writer.println("</tr>");
            }
        }
        writer.println("</table>");
        writer.println("</body>\n</html>");
    }
    
     /**
     * Method that prints database before last change
     *
     * @param lastModified is string with last modified date
     * @param historyData is string with historic database
     */
     public void displayHistoryData(String lastModified, String historyData) {
        writer.println("<html>\n<body>\n<h1>Last modified was: " + lastModified + "</h1> \n");
        writer.println("<h2>Database before change: " + "</h2>\n");
        writer.println("<table border=1><tr>" + "<th>Type</th>" + "<th>Category</th>" + "<th>Price</th>" + "</tr>");
        writer.println("<tr>");
        writer.println(historyData);
        writer.println("</table>");
        writer.println("</body>\n</html>");
    }
     
     /**
     * Method that prints form to back to the main site
     *
     */
     public void printBackToMainForm() {
         writer.println("<form action=\"/CarRentalWebApp\">");
         writer.println("<p>Back to main site: <input type=\"submit\" value=\"Back\">");
     }
}
