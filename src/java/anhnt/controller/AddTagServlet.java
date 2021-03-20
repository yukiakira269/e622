/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.tag.TagDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class AddTagServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = "ADD_TAG_PAGE";
        boolean result = false;
        try {
            String sTagId = request.getParameter("txtTagId");
            int tagId = Integer.parseInt(sTagId);
            String Description = request.getParameter("txtDescription");

            if (tagId < 0) {
                throw new NumberFormatException();
            }

            if (Description.length() > 15 || Description.isBlank()) {
                request.setAttribute("DESC_LENGTH_ERROR", "A tag description cannot be empty"
                        + " and should be below 15 characters");
            } else {
                TagDAO dao = new TagDAO();
                result = dao.addTag(tagId, Description);
            }
            if (result) {
                url = "MANAGE_TAG_PAGE";
            }

        } catch (SQLException ex) {
            System.out.println("DUPLICATED!!!");
            log("AddTagServlet SQL: " + ex.toString());
            //The duplication error will produce an error message containing the
            //keyword "duplicate"
            if (ex.getMessage().contains("duplicate")) {
                request.setAttribute("UNAVAILABLE_ERROR", "!!! THIS ID IS NOT AVAILABLE!"
                        + " PLEASE TRY AGAIN !!!");
            }
        } catch (NamingException ex) {
            log("AddTagServlet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (NumberFormatException ex) {
            //Custom action for NumberFormatException
            log("AddTagServlet NumberException: " + ex.toString());
            request.setAttribute("NUMBER_ERROR", "Error! Value not accepted! Please try again!");
            url = "ADD_TAG_PAGE";
        } catch (Exception ex) {
            log("AddTagServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
            url = "error";
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
