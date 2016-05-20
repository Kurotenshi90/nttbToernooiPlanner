package datasource.DAO.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Peter-Paul on 09/05/2016.
 */
public class Databaseproperties {
    Properties prop = new Properties();
    public InputStream input = null;
    public Databaseproperties(){
        try {

            String filename = "databaseproperties/DatabaseProperties";
            input = Databaseproperties.class.getClassLoader().getResourceAsStream(filename);

            if(input== null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public String getUsername(){
        return prop.getProperty("username");
    }
    public String getPassword(){
        return prop.getProperty("password");
    }
    public String getURL(){
        return prop.getProperty("url");
    }

    public String getDriver() {
        return prop.getProperty("driver");
    }
}
