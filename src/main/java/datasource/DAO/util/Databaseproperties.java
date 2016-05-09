package datasource.DAO.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Peter-Paul on 09/05/2016.
 */
public class Databaseproperties {
    Properties properties = new Properties();
    public  Databaseproperties() throws IOException, ClassNotFoundException {

        String resourceName = "database.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        }
        System.out.println(getURL());
        //roperties.load(Databaseproperties.class.getResourceAsStream("DatabaseProperties.properties"));
        //Class.forName(properties.getProperty("driver"));
    }

    public String getUsername(){
        return properties.getProperty("username");
    }
    public String getPassword(){
        return properties.getProperty("password");
    }
    public String getURL(){
        return properties.getProperty("url");
    }
}
