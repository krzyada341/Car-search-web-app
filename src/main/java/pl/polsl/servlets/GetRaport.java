/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.view.Printer;

/**
 * Main class of the servlet that getting from cookies and printing the database
 * and date before modify
 *
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
@WebServlet("/GetRaport")
public class GetRaport extends HttpServlet {

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
        Printer printer = new Printer(out);
        Cookie[] cookies = request.getCookies();
        String lastModified = "never";
        String historyData = "Nothing was edited";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastModified")) {
                    lastModified = cookie.getValue();
                    break;
                }
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("historyData")) {
                    historyData = cookie.getValue();
                    break;
                }
            }
        }
        printer.displayHistoryData(lastModified, historyData);
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
