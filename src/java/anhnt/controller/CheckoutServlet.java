/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.orders.OrdersDAO;
import anhnt.orders.OrdersDTO;
import anhnt.ordersDetail.OrdersDetailDAO;
import anhnt.ordersDetail.OrdersDetailDTO;
import anhnt.product.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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

            //1. The customer pushes the cart to the cashier
            HttpSession session = request.getSession();
            //If there is no cart, nothing to be done
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                //If the cart is empty, nothing to be done;
                if (cart != null) {
                    //2. The store retrieves user's information
                    String custName = request.getParameter("custName");
                    String custAddress = request.getParameter("custAddress");
                    if (!custName.trim().isEmpty() && !custAddress.trim().isEmpty()) {
                        if (custName.length() < 20 && custAddress.length() < 20) {
                            //Convert to correct format
                            java.util.Date javaDate = new java.util.Date();
                            java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());

                            //3. Store clerks retrieves the item
                            Map<Integer, Integer> items = cart.getItems();
                            //If there is no item, nothing to be done
                            if (items != null) {
                                //3. Store clerk creates the order
                                OrdersDAO dao = new OrdersDAO();
                                int lastOrderId = dao.getLastOrderId();
                                //4. Store clerk details the list of item in the cart
                                Set<Entry<Integer, Integer>> itemSet = items.entrySet();
                                dao.createNewOrder(++lastOrderId, custName, custAddress,
                                        itemSet, sqlDate);

                                //5. The customer is then given a receipt
                                OrdersDTO dto = new OrdersDTO(lastOrderId, custName,
                                        custAddress, sqlDate);
                                request.setAttribute("RECEIPT", dto);
                                OrdersDetailDAO detailDao = new OrdersDetailDAO();
                                List<OrdersDetailDTO> orderItemsList
                                        = detailDao.getOrderDetails(lastOrderId);
                                request.setAttribute("RECEIPT_DETAIL", orderItemsList);
                                //6. The customer exists the store and return the cart
                                session.removeAttribute("CART");
                                url = "CHECK_OUT_SUCCESS";
                            }//end if items
                        }//end if cust name length
                        else {
                            url = "CHECK_OUT_PAGE";
                            String lengthError = "Name/Address is excessively extended!!!";
                            request.setAttribute("LENGTH_ERROR", lengthError);
                        }
                    }//end if cust name
                    //If the needed information is not found, request for said information
                    else {
                        url = "CHECK_OUT_PAGE";
                        String emptyNameError = "Fullname and address must not be blank!!!";
                        request.setAttribute("EMPTY_ERROR", emptyNameError);
                    }
                }//end if cart
            }//end if session

        } catch (NamingException ex) {
            log("CheckoutServlet NamingException: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } catch (SQLException ex) {
            log("CheckoutServlet SQLException: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } catch (NullPointerException ex) {
            log("CheckoutServlet NullPointer: " + ex.toString());
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
