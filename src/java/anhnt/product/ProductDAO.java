/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.product;

import anhnt.utilities.DBHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.Base64;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class ProductDAO implements Serializable {

    private static List<ProductDTO> imageList;

    public List<ProductDTO> getImageList()
            throws NamingException, SQLException, IOException {
        imageList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //1. Establish connection
        con = DBHelper.makeConnection();
        if (con != null) {
            //2. Prepare SQL String
            String sql = "SELECT * FROM Product";
            stm = con.prepareStatement(sql);
            //3. Store in result set
            rs = stm.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt(1);
                int storeQuantity = rs.getInt(2);
                Blob blob = rs.getBlob("img");
                int tags = rs.getInt(4);
                int likes = rs.getInt(5);

                // Use blob object as input stream
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                //Read from the input stream into the buffer
                int bytesRead = inputStream.read(buffer);
                //while input stream can still be read into buffer (not EOF/-1), continue to read data
                while (bytesRead != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    bytesRead = inputStream.read(buffer);
                }
                //Convert data buffered into outputStream to a byteArray
                byte[] imageBytes = outputStream.toByteArray();
                //Encode the data into Base64 for
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                inputStream.close();
                outputStream.close();

                ProductDTO dto = new ProductDTO(productId, storeQuantity,
                        imageBytes, tags, likes, base64Image);
                imageList.add(dto);
            }
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

        return imageList;
    }

}
