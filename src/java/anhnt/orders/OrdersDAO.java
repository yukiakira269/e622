/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.orders;

import anhnt.ordersDetail.OrdersDetailDAO;
import anhnt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.*;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class OrdersDAO implements Serializable {

    public int getLastOrderId() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //1. Establish DB Connection
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                //Retrieves the last
                String sql = "SELECT TOP 1 orderId FROM Orders"
                        + " ORDER BY orderId DESC";
                stm = con.prepareStatement(sql);
                //3. Execute and store in result set
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
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

        return 0;
    }

    public void createNewOrder(int orderId, String custName, String custAddress,
            Set<Map.Entry<Integer, Integer>> products, Date date)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                /*2. Set auto commit to false in order to 
                ensure the integrity of the transaction 
                (no action is performed until the commit method is explicitly called)*/
                //3. Prepare SQL String
                String sql = "INSERT INTO Orders "
                        + "VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setString(2, custName);
                stm.setString(3, custAddress);
                stm.setDate(4, date);
                stm.executeUpdate();

                sql = "INSERT INTO OrdersDetail "
                        + "VALUES(?,?,?)";
                stm = con.prepareStatement(sql);
                for (Map.Entry<Integer, Integer> entry : products) {
                    stm.setInt(1, orderId);
                    stm.setInt(2, entry.getKey());
                    stm.setInt(3, entry.getValue());
                    //3. This method is intended only to be called by others, thus
                    //commit() is not called.
                    stm.executeUpdate();
                }

                con.commit();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }

    }

    public void createNewOrder(int orderId, String custName, String custAddress,
            int productId, int orderQuantity, Date date)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                /*2. Set auto commit to false in order to 
                ensure the integrity of the transaction 
                (no action is performed until the commit method is explicitly called)*/
                con.setAutoCommit(false);
                //3. Prepare SQL String
                String sql = "INSERT INTO Orders "
                        + "VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setString(2, custName);
                stm.setString(3, custAddress);
                stm.setDate(4, date);

                stm.executeUpdate();

                OrdersDetailDAO dao = new OrdersDetailDAO();
                dao.addOrderDetails(orderId, productId, orderQuantity);

                con.commit();

            }
        } catch (SQLException ex) {
            if (con != null) {
                try {
                    System.out.println("Something went wrong! "
                            + "Terminating the transaction");
                    con.rollback();
                    //Re-throw the exception for logging purposes
                    throw ex;
                } catch (SQLException e) {
                    System.out.println("Cannot rollback! "
                            + "Check logs for further details");
                    throw e;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }

    }
}
