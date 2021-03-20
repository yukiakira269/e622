/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.product.CartObject;
import anhnt.product.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class AddToCartServlet extends HttpServlet {

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
            //1. Go to carts holder
            HttpSession session = request.getSession();
            //2. Retrieve cart from its place
            CartObject cart = (CartObject) session.getAttribute("CART");
            //If the customer does not have a cart
            //retrieves a new cart
            if (cart == null) {
                cart = new CartObject();
            }
            //3. Customer selects an item
            String sProductId = request.getParameter("txtProductId");

            //4. Check to see if the wanted merchandise is still available
            int productId = Integer.parseInt(sProductId);
            ProductDAO dao = new ProductDAO();
            int currentQuantity = dao.getQuantity(productId);
            if (currentQuantity > 0) {
                //If the merchandise is still available
                //5. Customer put said item into the cart
                cart.addItemToCart(productId);
                //6. Update the cart
                session.setAttribute("CART", cart);
                //7. Update the number of items left in the store
                dao.updateProductQuantity(productId, dao.getQuantity(productId) - 1);
            }
            //Recall the search function
            String lastSearch = request.getParameter("lastTagValue");
            System.out.println(lastSearch);
            url = "tagSearch?txtTag=" + lastSearch;
        } catch (NamingException ex) {
            log("AddToCartSerlvet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (SQLException ex) {
            log("AddToCartSerlvet SQL: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (Exception ex) {
            log("AddToCartSerlvet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } finally {
            response.sendRedirect(url);
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
