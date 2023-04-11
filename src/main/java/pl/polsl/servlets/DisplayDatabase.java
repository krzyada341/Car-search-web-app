/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.model.Database;
import pl.polsl.view.Printer;

/**
 * Main class of the servlet displaying all or selected group of cars from
 * database
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
@WebServlet("/DisplayDatabase")
public class DisplayDatabase extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Database database = (Database) session.getAttribute("database");
        if (database == null) {
            out.println("Set database first!");
        } else {
            String input = request.getParameter("displayOption");
            if (input == null) {
                out.println("Select display option first!");
            } else {
                char group = input.charAt(0);
                Printer printer = new Printer(out);

                if (group == 'A' || group == 'B' || group == 'C') {
                    printer.displayFromCategory(database, group);
                } else {
                    printer.displayCars(database);
                }
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method. Works in the same way as doGet
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
