/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.product.ProductDAO;
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
public class UpdateBookServlet extends HttpServlet {

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
        String url = "error";
        try {
            String sProductId = request.getParameter("productId");
            String sStoreQuantity = request.getParameter("storeQuantity");
            String productDesc = request.getParameter("productDesc");
            if (!sStoreQuantity.trim().isEmpty() && !productDesc.trim().isEmpty()) {
                int productId = Integer.parseInt(sProductId);
                int storeQuantity = Integer.parseInt(sStoreQuantity);
                if (storeQuantity < 0 || storeQuantity > 1000) {
                    throw new NumberFormatException();
                }
                if (productDesc.length() > 50) {
                    String descLength = "Please briefly describe the product (less than 50 characters)!!";
                    request.setAttribute("LENGTH_ERROR", descLength);
                }

                ProductDAO dao = new ProductDAO();
                dao.updateProductQuantity(productId, storeQuantity);
                dao.updateProductDesc(productId, productDesc);
            } else {
                String empty = "Quantity and product must not be empty!!";
                request.setAttribute("EMPTY_ERROR", empty);
            }
            url = "MANAGE_SHOP_PAGE";

        } catch (SQLException ex) {
            log("UpdateBookServlet SQL: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (NamingException ex) {
            log("UpdateBookServlet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } catch (NumberFormatException ex) {
            //Custom action for NumberFormatException
            log("UpdateBookServlet NumberException: " + ex.toString());
            request.setAttribute("NUMBER_ERROR", "Error! Only a positive numerical value "
                    + "below 1000 is accepted");
            url = "MANAGE_SHOP_PAGE";
        } catch (Exception ex) {
            log("UpdateBookServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
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
