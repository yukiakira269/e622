/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.registration;

import anhnt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class RegistrationDAO implements Serializable {

    public List<String> getColumnNames()
            throws SQLException, NamingException {
        List<String> columnNames = new ArrayList<String>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT * FROM Registration";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                //3. Store result in result set
                if (rs.next()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    //4. SQL column starts from 1
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnName = rsmd.getColumnName(i);
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

    private List<RegistrationDTO> accountList = null;

    public List<RegistrationDTO> searchAccount(String userId)
            throws NamingException, SQLException {

        //1. Establish connection
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL String
                String sql = "SELECT userId,password,fullname,isAdmin "
                        + "FROM Registration "
                        + "WHERE userId LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + userId + "%");
                rs = stm.executeQuery();
                accountList = new ArrayList<RegistrationDTO>();
                while (rs.next()) {
                    String usrId = rs.getString(1);
                    String pass = rs.getString(2);
                    String name = rs.getString(3);
                    boolean role = rs.getBoolean(4);
                    RegistrationDTO dto = new RegistrationDTO(usrId,
                            pass, name, role);
                    accountList.add(dto);
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

        return accountList;
    }

    public boolean updateAccount(String userId, String password, boolean isAdmin)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ? "
                        + "WHERE userId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, userId);
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

    public boolean deleteAccount(String userId)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                String sql = "DELETE "
                        + "FROM Registration "
                        + "WHERE userId LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, userId);
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
