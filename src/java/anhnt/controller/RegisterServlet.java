/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.registration.RegistrationDAO;
import anhnt.registration.CreateErrorObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author DELL
 */
public class RegisterServlet extends HttpServlet {

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
        String userId = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        CreateErrorObject error = new CreateErrorObject();
        boolean errorFound = false;
        String url = "REGISTER_PAGE";

        PrintWriter out = response.getWriter();
        try {
            if (userId != null && password != null && confirm != null && fullname != null) {
                //1. Check for valid user inputs
                if (userId.trim().length() < 5 || userId.trim().length() > 15) {
                    errorFound = true;
                    error.setUsernameLengthErr("!!! USERNAME MUST BE BETWEEN 5"
                            + " AND 15 CHARACTERS");
                }//end if userId
                if (password.trim().length() < 5 || password.trim().length() > 15) {
                    errorFound = true;
                    error.setPasswordLengthErr("!!! PASSWORD MUST BE BETWEEN 5"
                            + " AND 15 CHARACTERS");
                }//end if password
                if (!password.equals(confirm)) {
                    errorFound = true;
                    error.setConfirmNotMatchErr("!!! CONFIRMATION FAILED !!!");
                }//end if !password
                if (fullname.trim().length() < 5 || fullname.trim().length() > 40) {
                    errorFound = true;
                    error.setFullnameLengthErr("!!! NAME FORMAT UNSUPPORTED !!!");
                }//end if fullname
                //2. If invalid, redirect to error page
                if (errorFound) {
                    url = "REGISTER_PAGE";
                    request.setAttribute("ERROR", error);
                    //3. if valid, call DAO methods
                } else {
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.registerAccount(userId, password, fullname, false);
                    if (result) {
                        url = "login";
                    }//end if restul
                }
            }//end if userId is null

        } catch (SQLException ex) {
            System.out.println("DUPLICATED!!!");
            log("RegisterServlet SQL: " + ex.getMessage());
            //The duplication error will produce an error message containing the
            //keyword "duplicate"
            if (ex.getMessage().contains("duplicate")) {
                error.setUsernameDuplicatedErr("!!! THIS USERNAME HAS BEEN TAKEN!"
                        + " PLEASE TRY AGAIN !!!");
                request.setAttribute("ERROR", error);
            }
        } catch (NamingException ex) {
            log("RegisterServlet Naming: " + ex.getCause());
        } catch (Exception ex) {
            log("RegisterServlet Exception: " + ex.toString());
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
