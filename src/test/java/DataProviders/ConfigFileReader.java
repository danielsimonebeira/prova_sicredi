package DataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "src/test/resources/Features/Configuration.properties";

    public ConfigFileReader() {
        BufferedReader leia;
        try {
            leia = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(leia);
                leia.close();

            } catch (IOException e) {
                e.printStackTrace();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties nao encontrado em " + propertyFilePath);

        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driver.path");
        if (driverPath != null) {
            return driverPath;

        } else {
            throw new RuntimeException("driver.path não encontrado no arquivo Configutarion.properties");

        }
    }

    public String getApplicationUrl(String url) {
        if (url.equals("url.e2e")) {
            return url =properties.getProperty("url.e2e");

        } else if (url.equals("url.api")) {
            return url = properties.getProperty("url.api");

        } else {
            throw new RuntimeException("url não encontrado no arquivo Configutarion.properties");

        }
    }
}
