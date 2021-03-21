/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnt.listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author DELL
 */
public class AppStartListener implements ServletContextListener {

    /**
     *
     * @param sce
     * @throws FileNotFoundException
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        File f = null;
        Scanner sc = null;
        Map<String, String> dirMap = null;
        String line;
        String action, resource;
        ServletContext ctx = sce.getServletContext();
        InputStream is = null;
        try {
//            f = new File("C:\\Users\\DELL\\Documents\\NetBeansProjects\\AssignmentAnhnt\\web\\src\\main\\webapp\\dir.txt");
            //Use InputStream instead of File as File is dependent on
            //the working directory, which may vary based on the server
            is = AppStartListener.class.getResourceAsStream("dir.txt");
            //Initiate a Scanner object to read 
            //from text file with supported methods
            sc = new Scanner(is);

            if (sc != null) {
                //If the resource is available
                //Initiate the Map object to store directory links
                dirMap = new HashMap<String, String>();
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    if (!line.isEmpty()) {
                        action = line.substring(0, line.lastIndexOf("="));
                        resource = line.substring(line.lastIndexOf("=") + 1);
                        dirMap.put(action.trim(), resource.trim());
                    }//end if line
                }//end while sc
            }//end if sc
            ctx.setAttribute("DIRMAP", dirMap);

            //Handle file uploading
            //Return the path of the server
//            String rootPath = System.getProperty("catalina.home");
            //Return the path relative to the Project's folder?
            String rootPath = ctx.getRealPath("");
            String relativePath = ctx.getInitParameter("ImageFolder");
            System.out.println(rootPath);
//            File file = new File(rootPath + File.separator + ".." + File.separator + ".." + File.separator + relativePath);
            File file = new File(rootPath + File.separator + relativePath);
            //Configure to run directly from the Project's folder with NetBeans
            if (rootPath.contains("build")) {
                File backup = new File(rootPath + File.separator + ".." + File.separator + ".." + File.separator + "web" + File.separator + relativePath);
                if (!backup.exists()) {
                    backup.mkdirs();
                }//end if !backup
                ctx.setAttribute("BAK_FILE", backup);
            }//end if rootPath
            if (!file.exists()) {
                file.mkdirs();
            }//end if !file
            ctx.setAttribute("DIR_FILE", file);

        } finally {
            if (sc != null) {
                sc.close();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    ctx.log("AppStartListner IOException: " + ex.getMessage());
                }
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("App undeploying... Thank you for using our service");

    }
}
