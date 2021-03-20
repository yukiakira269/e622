/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.product.ProductDAO;
import anhnt.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author DELL
 */
public class TagSearchServlet extends HttpServlet {

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
        try {
            request.setCharacterEncoding("UTF-8");
            String Description = request.getParameter("txtTag");
            String txtAdmin = request.getParameter("txtAdmin");
            if (!Description.trim().isEmpty()) {
                ProductDAO dao = new ProductDAO();
                List<ProductDTO> bookList = dao.searchByTagAndDesc(Description);
                request.setAttribute("TAG_SEARCH", bookList);
            } else {
                request.setAttribute("EMPTY_ERROR", "Note: Fill this box to look"
                        + " for specific entry!");
            }

            //Redirect to the correct page
            urlRewrite = "SHOP_PAGE?txtTag=" + Description;
            if (txtAdmin != null) {
                urlRewrite = "MANAGE_SHOP_PAGE?txtTag=" + Description;
            }
        } catch (SQLException ex) {
            log("TagSearchServlet SQL: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
            
        } catch (NamingException ex) {
            log("TagSearchServlet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
            
        } catch (Exception ex) {
            log("TagSearchServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } finally {
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
