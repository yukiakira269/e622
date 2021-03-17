/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class LoginServlet extends HttpServlet {

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
        String url = "LOGIN_PAGE";
        HttpSession session = request.getSession();
        try {
            String userId = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            //If the client chooses to remember password
            String sAutoLogin = request.getParameter("checkRemember");
            //Execute only if the userId and password is not null
            if (userId != null && password != null) {
                if (sAutoLogin != null) {
                    Cookie cookie = new Cookie(userId, password);
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie);
                }
                RegistrationDAO dao = new RegistrationDAO();
                int result = dao.checkLogin(userId, password);
                //Session scope to use the information in succesive requests
                String fullname = dao.getFullname(userId);
                session.setAttribute("FULLNAME", fullname);
                //If the account is an adminstrative account, forward to account search page
                if (result == 1) {
                    url = "SEARCH_PAGE";
                } //If the account is a normal user account, forward to the gallery page
                else if (result == 0) {
                    url = "SHOP_PAGE";
                }
                //If the result is -1, meaning no account found, forward to the login page by default
            }//end if userId null

        } catch (NamingException ex) {
            log("LoginServlet Naming: " + ex.getCause());

        } catch (SQLException ex) {
            log("LoginServlet SQL: " + ex.getCause());

        } catch (Exception ex) {
            log("LoginServlet UnknownException: " + ex.toString());
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
