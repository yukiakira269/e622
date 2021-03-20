/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class UpdateServlet extends HttpServlet {

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
        String urlRewrite = "error";
        HttpSession session = request.getSession();
        try {
            String userId = request.getParameter("txtUserId");
            String password = request.getParameter("txtPassword");
            String sIsAdmin = request.getParameter("checkAdmin");
            boolean isAdmin = false;
            //Execute only if params are not null
            if (userId != null && password != null) {
                RegistrationDAO dao = new RegistrationDAO();
                String lastSearchValue = request.getParameter("lastSearchValue");
                urlRewrite = "search?txtSearch=" + lastSearchValue;
                // Is the checkbox is check, sIsAdmin will not be null
                if (sIsAdmin != null) {
                    isAdmin = true;
                }

                //Check for password length error, synchronous with the RegisterServlet
                if (password.trim().length() < 5 || password.trim().length() > 15) {
                    String passwordLengthError = "PASSWORD LENGTH VIOLATED! "
                            + "MUST BE BETWEEN 5 AND 15 CHARACTERS";
                    request.setAttribute("PASS_LENGTH_ERR", passwordLengthError);

                } else {
                    //if no error is found, update the account
                    dao.updateAccount(userId, password, isAdmin);
                    //Update the status of the current account if changed (if any)
                    session.setAttribute("ADMIN_STATUS", dao.getstatus(
                            (String) session.getAttribute("FULLNAME")));
                }
            }//end if userId 
        } catch (SQLException ex) {
            log("UpdateServlet SQL: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (NamingException ex) {
            log("UpdateServlet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (Exception ex) {
            log("UpdateServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } finally {
//          response.sendRedirect(urlRewrite);
            //Use request dispatcher to maintain attributes the request scope (if any)
            RequestDispatcher rd = request.getRequestDispatcher(urlRewrite);
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
