/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.registration;

import anhnt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.*;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class RegistrationDAO implements Serializable {

    public String getFullname(String userId)
            throws NamingException, SQLException {
        String fullname = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT fullname FROM Registration WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                //3. Execute and store in result set
                rs = stm.executeQuery();
                if (rs.next()) {
                    fullname = rs.getString(1);
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
        return fullname;
    }

    public boolean checkLogin(String userId, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                String sql = "SELECT * "
                        + "FROM Registration "
                        + "WHERE userId = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, password);
                //3. Execute query and store in result set
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
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

        return false;
    }

    public boolean registerAccount(String userId, String password, String fullname,
            boolean role) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL string
                String sql = "INSERT INTO Registration(userId, password, fullname, isAdmin) "
                        + "VALUES(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
                stm.setString(2, password);
                stm.setString(3, fullname);
                stm.setBoolean(4, role);
                //3. Execute
                int affected = stm.executeUpdate();
                if (affected > 0) {
                    return true;
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
        return false;
    }

}
