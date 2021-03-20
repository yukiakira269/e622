/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.tag;

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
public class TagDAO implements Serializable {

    /**
     * Return a matching tagId based on the Description
     *
     * @param Description
     * @return
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    public String matchTag(int tagId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String desc = "Not Found";
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                String sql = "SELECT description FROM Tag WHERE tagId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tagId);
                //3. Store results in ResultSet
                rs = stm.executeQuery();
                if (rs.next()) {
                    desc = rs.getString(1);
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

        return desc;
    }

    private static List<TagDTO> tagList;

    public List<TagDTO> searchTag(String Description) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                tagList = new ArrayList<>();
                //2. Prepare sql string
                String sql = "SELECT tagId, description FROM Tag WHERE description LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + Description + "%");
                //3. Store results in ResultSet
                rs = stm.executeQuery();
                while (rs.next()) {
                    TagDTO dto = new TagDTO(rs.getInt(1), rs.getString(2));
                    tagList.add(dto);
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

        return tagList;
    }

    /**
     * Return all tags based on tagId
     *
     * @param tagId
     * @return
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    public List<TagDTO> searchTag(int tagId) throws NamingException, SQLException {
        String Description = "";
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                tagList = new ArrayList<>();
                //2. Prepare sql string
                String sql = "SELECT tagId, description FROM Tag WHERE tagId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tagId);
                //3. Store results in ResultSet
                rs = stm.executeQuery();
                while (rs.next()) {
                    TagDTO dto = new TagDTO(rs.getInt(1), rs.getString(2));
                    tagList.add(dto);
                    return tagList;
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

        return tagList;
    }

    public boolean updateTag(int tagId, String Description)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                String sql = "UPDATE Tag "
                        + "SET description = ? "
                        + "WHERE tagId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, Description);
                stm.setInt(2, tagId);
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

    public boolean addTag(int tagId, String Description)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare SQL string
                String sql = "INSERT INTO Tag(tagId,description) "
                        + "VALUES(?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tagId);
                stm.setString(2, Description);

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

    public boolean deleteTag(int tagId)
            throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Prepare sql string
                //Delete all dependencies
                String sql = "DELETE "
                        + "FROM Product "
                        + "WHERE tags = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tagId);
                stm.executeUpdate();

                //Delete the tag
                sql = "DELETE "
                        + "FROM Tag "
                        + "WHERE tagId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, tagId);
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

    public List<TagDTO> getAllTag() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Establish connection
            con = DBHelper.makeConnection();
            if (con != null) {
                tagList = new ArrayList<TagDTO>();
                //2. Prepare sql string
                String sql = "SELECT tagId, description "
                        + "FROM Tag";
                stm = con.prepareStatement(sql);
                //3. Execute and store results
                rs = stm.executeQuery();
                while (rs.next()) {
                    int tagId = rs.getInt(1);
                    String Description = rs.getString(2);
                    TagDTO dto = new TagDTO(tagId, Description);
                    tagList.add(dto);
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

        return tagList;
    }

}
