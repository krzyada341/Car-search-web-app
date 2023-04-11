/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.model.Car;
import pl.polsl.model.Database;
import pl.polsl.model.ParametersException;
import pl.polsl.view.Printer;

/**
 * Main class of the servlet that changing price for a selected cars category in
 * database
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
@WebServlet("/ChangePrice")
public class ChangePrice extends HttpServlet {

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
            throws IOException, ServletException {
        response.setContentType("text/html; charset=ISO-8859-2");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        Database database = (Database) session.getAttribute("database");
        Printer printer = new Printer(out);

        if (database == null) {
            out.println("Set database first!");
        } else {
            String historyData = "";
            for (Car n : database.getDatabase()) {
                historyData += "<td>" + n.getType() + "</td>";
                historyData += "<td>" + n.getCategory() + "</td>";
                historyData += "<td>" + n.getPrice() + "</td>";
                historyData += "</tr>";
            }
            Cookie lastModifiedCookie = new Cookie("lastModified", new java.util.Date().toString());
            Cookie historyDataCookie = new Cookie("historyData", historyData);

            response.addCookie(lastModifiedCookie);
            response.addCookie(historyDataCookie);

            String input = request.getParameter("groupOption");
            String price = request.getParameter("priceValue");
            if (input == null) {
                out.println("Select group!");
            } else {
                char group = input.charAt(0);
                try {
                    database.setGroupPrice(group, price);
                } catch (ParametersException | NullPointerException ex) {
                    out.println("Type price value!");
                }
                if (price != "") {
                    session.setAttribute("database", database);
                    out.println("<h1>Database changed succesfully!</h1>");
                    printer.printBackToMainForm();
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
