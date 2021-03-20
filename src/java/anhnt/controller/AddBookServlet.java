/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.controller;

import anhnt.product.CreateBookError;
import anhnt.product.ProductDAO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author DELL
 */
@MultipartConfig
public class AddBookServlet extends HttpServlet {

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
        String url = "ADD_BOOK_PAGE";
        boolean result = false;
        CreateBookError errs = new CreateBookError();
        boolean errorFound = false;
        try {
            String sProductId = request.getParameter("txtProductId");
            System.out.println(sProductId);
            String sStoreQuantity = request.getParameter("txtStoreQuantity");
            System.out.println(sStoreQuantity);
            String sTagId = request.getParameter("txtTagId");
            System.out.println(sTagId);
            String productDesc = request.getParameter("txtProductDescription");
            System.out.println(productDesc);
            Part filePart = request.getPart("imageFile");

            //Add only when there is enough information
            if (!sProductId.trim().isEmpty() && !sStoreQuantity.trim().isEmpty()
                    && !sTagId.trim().isEmpty() && !productDesc.trim().isEmpty()
                    && filePart.getSize() > 0) {
                //Convert the data to the appropriate format
                int productId = Integer.parseInt(sProductId);
                int storeQuantity = Integer.parseInt(sStoreQuantity);
                if (storeQuantity < 0 || storeQuantity > 1000) {
                    errs.setNumberFormatError("Error! Quantity must be less than 1000");
                    errorFound = true;
                }
                int tagId = Integer.parseInt(sTagId);
                if (productDesc.length() > 50) {
                    errs.setDescLengthError("Please briefly describe the product"
                            + " (less than 50 characters)!!");
                    errorFound = true;
                }
                //Call DAO if all RELEVANT conditions have been fulfilled and no exceptions happened
                ProductDAO dao = new ProductDAO();
                if (!errorFound) {
                    result = dao.addBook(productId, storeQuantity, tagId, productDesc);
                }
                //Handle uploaded images
                //Upload directory
                ServletContext ctx = this.getServletContext();
                File uploadDir = (File) ctx.getAttribute("DIR_FILE");
                File backupDir = (File) ctx.getAttribute("BAK_FILE");

                System.out.println("UPLOAD DIR:" + uploadDir);
                //Retrieve the file sent in parts from the jsp page
                //The new book's ID, set automatically
                int newId = dao.getLastBookId();
                //Convert to .png (hard-coded in the jsp and html pages)
                String fileName = Integer.toString(newId) + ".png";
                //Write file to the predetermined server path
                /*Set up a new File object to represent the uploading directory 
                    (File can represent both files or pathnames)*/
                File uploads = new File(uploadDir.getAbsolutePath());
                File bakUploads = new File(backupDir.getAbsolutePath());
                //Create a new File Object with the given name to contain the incoming data
                File f = new File(fileName);
                //Combine the pathname and the new file's name into one unified File instance
                File file = new File(uploads, f.getName());
                File backupFile = new File(bakUploads, f.getName());
                //Retrieve inputStream from the obtained data parts
                InputStream input = filePart.getInputStream();
                InputStream backupInput = filePart.getInputStream();

                //Copy all bytes from the input stream to the specified file (path)
                try {
                    Files.copy(input, file.toPath());
                    System.out.println("COPY1");
                    Files.copy(backupInput, backupFile.toPath());
                    System.out.println("COPY2");
                } finally {
                    //Close the input stream
                    input.close();
                    backupInput.close();
                }

            }//end if sProduct
            else {
                errs.setEmptyFieldsError("Please fill in all the required information!!");
                errorFound = true;
            }

            //If the book is added
            if (result) {
                url = "MANAGE_SHOP_PAGE";
            } else {
                request.setAttribute("NOT_ADDED", "BOOK NOT ADDED PLEASE TRY AGAIN");
            }
            //Else errors happen, refresh the page with the errors printed

        } catch (SQLException ex) {
            ex.printStackTrace();
            String errorMsg = ex.getMessage();
            if (errorMsg.contains("conflicted")) {
                String err = "TAG ID NOT FOUND! PLEASE REFER TO THE TAG LIST";
                System.out.println(err);
                errs.setIdNotFoundError(err);
                errorFound = true;
            }
            log("AddBookServlet SQL: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } catch (NamingException ex) {
            log("AddBookServlet Naming: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } catch (NumberFormatException ex) {
            //Custom action for NumberFormatException
//            errs.setNumberFormatError("Invalid values for quantity/Id");
//            errorFound = true;
            request.setAttribute("NUMBER_ERROR", "Invalid values for quantity/Id");
            log("AddBookServlet NumberException: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());

        } catch (Exception ex) {
            log("AddBookServlet Exception: " + ex.toString());
            request.setAttribute("OMNI_ERROR", ex.toString());
        } finally {
            if (errorFound) {
                request.setAttribute("ADD_BOOK_ERROR", errs);
            }
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
