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
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author DELL
 */
public class RemoveItemServlet extends HttpServlet {

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
            //Customer goes to cart
            HttpSession session = request.getSession();
            //If the session is null, there is nothing to be done
            if (session != null) {
                //Customer retrieves the cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                //If the cart is empty, there is nothing to be done
                if (cart != null) {
                    //Customer retrieves list of unwanted items
                    String[] removedItems = request.getParameterValues("chkRemoved");
                    //If the list has no item, there is nothing to be done
                    if (removedItems != null) {
                        for (String item : removedItems) {
                            int productId = Integer.parseInt(item);
                            cart.removeFromCart(productId);
                            //Update the items in the store accordingly
                            ProductDAO dao = new ProductDAO();
                            String sQuantityRemoved = request.getParameter("quantityRemoved");
                            int quantityRemoved = Integer.parseInt(sQuantityRemoved);
                            dao.updateProductQuantity(productId,
                                    dao.getQuantity(productId) + quantityRemoved);
                        }
                    }
                }
            }//end if session
            url = "VIEW_CART_PAGE";
        } catch (NamingException ex) {
            log("RemoveItemServlet Naming: " + ex.getCause());
        } catch (SQLException ex) {
            log("RemoveItemServlet SQL: " + ex.getCause());
        } catch (Exception ex) {
            log("SearchServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
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
