/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.utilities;

import java.sql.*;
import java.io.Serializable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author DELL
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection()
            throws NamingException, SQLException {
        //1. Retrieve current OS' context (working environment)
        Context currentCtx = new InitialContext();
        //2. Retrieve Tomcat's OS' context
        Context tomcatContext = (Context) currentCtx.lookup("java:comp/env");
        //3. Retrieve defined Data Source
        DataSource ds = (DataSource) tomcatContext.lookup("DBCon");
        //4. Connect
        Connection con = ds.getConnection();
        return con;
    }

}
