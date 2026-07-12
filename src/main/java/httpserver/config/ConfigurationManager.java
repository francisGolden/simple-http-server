package httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import httpserver.exception.HttpConfigurationException;
import httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// We're using a Singleton because we don't need more than one configuration manager.
// We just need one, which is going to be shared across the project.
public class ConfigurationManager {
    // This is the instance that we're going to be using.
    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){
    }

    public static ConfigurationManager getInstance(){
        if (myConfigurationManager == null){
            myConfigurationManager = new ConfigurationManager();
        }
        return myConfigurationManager;
    }

    // Used to load a configuration file by the path provided
    public void loadConfigurationFile(String filePath) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e){
            throw new HttpConfigurationException(e.getMessage());
        }

        StringBuffer stringBuffer = new StringBuffer();

        int i;

        try {
            while((i = fileReader.read()) != -1){
                stringBuffer.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }

        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the configuration file", e);
        }

        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the configuration file, internal", e);
        }
        
        fileReader.close();
    }

    // Returns the current loaded configuration
    public Configuration getCurrentConfiguration(){
        if (myCurrentConfiguration == null){
            throw new HttpConfigurationException("No currentConfiguration set");
        }
        return myCurrentConfiguration;
    }
}
