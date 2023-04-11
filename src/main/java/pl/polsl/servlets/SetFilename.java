/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.servlets;

import java.io.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import pl.polsl.model.Database;
import pl.polsl.view.Printer;

/**
 * Main class of the servlet that setting database filename given during servlet
 * initialization
 *
 * @author Krzysztof Adamczyk
 * @version 4.0
 */
@WebServlet("/SetFilename")
public class SetFilename extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        Printer printer = new Printer(out);
        String filename = request.getParameter("filename");

        Database database = new Database();
        ServletContext application = getServletConfig().getServletContext();
        String filePath = application.getRealPath("/database/" + filename);
        database.setFilename(filePath);

        try {
            database.setDatabase();
        } catch (FileNotFoundException | NullPointerException ex) {
            out.println("Filename not exist!");
        }
        if (database.getDatabase().isEmpty() == false) {
            session.setAttribute("database", database);
            out.println("<h1>Database added succesfully!</h1>");
            printer.printBackToMainForm();
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
