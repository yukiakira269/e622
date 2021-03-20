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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AutoLogServlet extends HttpServlet {

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
        int result = -1;
        String url = "FIRST_LOGIN_PAGE";
        System.out.println("AUTOLOGGING");
        try {
            //1. Retrieve clients' cookies
            Cookie[] cookies = request.getCookies();
            //2. If the clients do have cookies
            if (cookies != null) {
                //3. Retrieve the last cookie created by the client
                Cookie lastCookie = cookies[cookies.length - 1];
                //If there is at least 2 cookies, in which the last is JSESSIONID
                //The last cookie would be positioned just before the JSESSIONID
                if (lastCookie.getName().equals("JSESSIONID") && cookies.length > 1) {
                    lastCookie = cookies[cookies.length - 2];
                }//end if lastCookie
                String userId = lastCookie.getName();
                String password = lastCookie.getValue();
                RegistrationDAO dao = new RegistrationDAO();
                result = dao.checkLogin(userId, password);
            }//end if Cookies
            
            //If the account is an adminstrative account, forward to account search page
            if (result == 1) {
                url = "SEARCH_PAGE";
                HttpSession session = request.getSession();
                session.setAttribute("ADMIN_STATUS", true);
            } //If the account is a normal user account, forward to the gallery page
            else if (result == 0) {
                url = "SHOP_PAGE";
                //If this is the first login of the session (i.e no cookies is found),
                //forward to login page
            }
            //If the result is -1, meaning no account found,
            //forward to the login page by default
        } catch (NamingException ex) {
            log("AutoLogServlet Naming: " + ex.toString());
        } catch (SQLException ex) {
            log("AutoLogServlet SQL:" + ex.toString());
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
