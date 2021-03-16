/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.product;

import anhnt.tag.TagDAO;
import anhnt.utilities.DBHelper;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable {

    private static List<String> columnNames;

    public List<String> getColumnNames() throws SQLException, NamingException {
        columnNames = new ArrayList<String>();
        //1. Establish connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT TOP 1 productId,storeQuantity,tags,productDesc "
                        + "FROM Product "
                        + "ORDER BY storeQuantity";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String columnName;
                    //SQL indices start from 1
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        columnName = rsmd.getColumnName(i);
                        columnNames.add(columnName);
                    }
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return columnNames;
    }

    private static List<ProductDTO> bookList;

    public List<ProductDTO> getBookList()
            throws NamingException, SQLException, IOException {
        bookList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //1. Establish connection
        con = DBHelper.makeConnection();
        if (con != null) {
            //2. Prepare SQL String
            String sql = "SELECT TOP 3 productId, storeQuantity, tags, productDesc"
                    + " FROM Product";
            stm = con.prepareStatement(sql);
            //3. Store in result set
            rs = stm.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                int storeQuantity = rs.getInt(2);
                int tags = rs.getInt(3);
                String productDesc = rs.getString(4);
                ProductDTO dto = new ProductDTO(productId, storeQuantity, tags, productDesc);
                bookList.add(dto);
            }
//            while (rs.next()) {
//                int productId = rs.getInt(1);
//                int storeQuantity = rs.getInt(2);
//                Blob blob = rs.getBlob("img");
//                int tags = rs.getInt(4);
//                int likes = rs.getInt(5);
//
//                
//                //EXPERIMENTAL ONLY!!! PUBLIC IMAGES SHOULD NOT BE SAVED IN DB
//                //BUT INSTEAD SHOULD BE SAVE DIRECTLY AT THE SERVER
//                // Use blob object as input stream
//                InputStream inputStream = blob.getBinaryStream();
//                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[4096];
//                //Read from the input stream into the buffer
//                int bytesRead = inputStream.read(buffer);
//                //while input stream can still be read into buffer (not EOF/-1), continue to read data
//                while (bytesRead != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                    bytesRead = inputStream.read(buffer);
//                }
//                //Convert data buffered into outputStream to a byteArray
//                byte[] imageBytes = outputStream.toByteArray();
//                //Encode the data into Base64 for
//                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//                inputStream.close();
//                outputStream.close();
//
//                ProductDTO dto = new ProductDTO(productId, storeQuantity,
//                        imageBytes, tags, likes, base64Image);
//                imageList.add(dto);
//            }
        }
        try {

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return bookList;
    }

    public List<ProductDTO> searchByTag(String Description)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        bookList = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            //1. Establish DB Connection
            if (con != null) {
                //Find the tag ID based on the Description
                TagDAO tagDAO = new TagDAO();
                int tagId = tagDAO.matchTag(Description);
                //If the tag is found
                if (tagId >= 0) {
                    //2. Prepare SQL String
                    String sql = "SELECT productId, storeQuantity, tags, productDesc "
                            + "FROM Product "
                            + "WHERE tags = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, tagId);
                    //3. Store in Result Set
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        int prductId = rs.getInt(1);
                        int storeQuantity = rs.getInt(2);
                        int tags = rs.getInt(3);
                        String productDesc = rs.getString(4);
                        ProductDTO dto = new ProductDTO(prductId, storeQuantity,
                                tags, productDesc);
                        bookList.add(dto);
                    }
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return bookList;
    }

    public int getQuantity(int productId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //1. Establish connection
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL string
                String sql = "SELECT storeQuantity FROM Product WHERE productId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, productId);
                //3. Execute and store results
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public boolean updateProductQuantity(int productId, int storeQuantity)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        //1. Establish connection
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL string
                String sql = "UPDATE Product "
                        + "SET storeQuantity = ? "
                        + "WHERE productId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, storeQuantity);
                stm.setInt(2, productId);
                //3. Execute
                int affected = stm.executeUpdate();
                if (affected > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    /**
     * Retrieve the product's description based on its ID
     */
    public String getProductDesc(int productId)
            throws NamingException, SQLException {
        ProductDTO dto;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            //2. Prepare sql string
            String sql = "SELECT productDesc FROM Product WHERE productId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, productId);
            //3. Execute and store in result set
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return null;
    }
}
