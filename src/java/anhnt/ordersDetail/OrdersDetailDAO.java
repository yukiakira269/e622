/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.ordersDetail;

import anhnt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class OrdersDetailDAO implements Serializable {

    public void addOrderDetails(int orderId, int productId,
            int orderQuantity) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish DB Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                con.setAutoCommit(false);
                //2. Prepare SQL String
                String sql = "INSERT INTO OrdersDetail "
                        + "VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setInt(2, productId);
                stm.setInt(3, orderQuantity);
                //3. This method is intended only to be called by others, 
                //commit() is not called.
                stm.executeUpdate();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * This method retrieves the order's detail based on input ID
     *
     * @param orderId
     * @return
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    private static List<OrdersDetailDTO> orderItemsList;

    public List<OrdersDetailDTO> getOrderDetails(int orderId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        orderItemsList = new ArrayList<OrdersDetailDTO>();
        try {
            //1. Establish DB Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT productId, orderQuantity "
                        + "FROM OrdersDetail "
                        + "WHERE orderId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                //3. Execute and store in result set
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt(1);
                    System.out.println(productId);
                    int orderQuantity = rs.getInt(2);
                    System.out.println(orderQuantity);
                    OrdersDetailDTO dto = new OrdersDetailDTO(orderId,
                            productId, orderQuantity);
                    orderItemsList.add(dto);
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
        return orderItemsList;
    }

}
