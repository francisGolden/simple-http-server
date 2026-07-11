package httpserver.config;

// We're using a Singleton because we don't need more than one configuration manager.
// We just need one, which is going to be shared across the project.
public class ConfigurationManager {
    // This is the instance that we're goint to be using.
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
    public void loadConfigurationFile(String filePath){

    }

    // Returns the current loaded configuration
    public void getCurrentConfiguration(){

    }
}
