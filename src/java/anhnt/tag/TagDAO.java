/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.tag;

import anhnt.utilities.DBHelper;
import java.io.Serializable;
import java.sql.*;
import javax.naming.NamingException;

/**
 *
 * @author DELL
 */
public class TagDAO implements Serializable {

    /**
     * Return a matching tagId based on the Description
     */
    public int matchTag(String Description) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            //1. Establish connection
            con = DBHelper.makeConnection();
            //2. Prepare sql string
            String sql = "SELECT tagId FROM Tag WHERE Description LIKE ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, Description);
            //3. Store results in ResultSet
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return -1;
    }

    /**
     * Return tag Description based on tagId
     */
    public String matchTag(int tagId) throws NamingException, SQLException {
        String Description = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {

            //1. Establish connection
            con = DBHelper.makeConnection();
            //2. Prepare sql string
            String sql = "SELECT Description FROM Tag WHERE tagId = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, tagId);
            //3. Store results in ResultSet
            rs = stm.executeQuery();
            if (rs.next()) {
                Description = rs.getString(1);
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return Description;
    }

}
