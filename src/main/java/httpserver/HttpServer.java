package httpserver;

import httpserver.config.Configuration;
import httpserver.config.ConfigurationManager;
import httpserver.exception.HttpConfigurationException;

import java.io.IOException;

// **
// * Driver Class for the Http Server
// **
public class HttpServer {
    public static void main (String[] args){
        System.out.println("hello world");

        try {
            ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
            Configuration configuration = ConfigurationManager.getInstance().getCurrentConfiguration();
            System.out.println("port: " + configuration.getPort() + ", webroot: " + configuration.getWebroot());
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }


    }
}
