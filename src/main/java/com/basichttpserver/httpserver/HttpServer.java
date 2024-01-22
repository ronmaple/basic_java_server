package com.basichttpserver.httpserver;

import com.basichttpserver.httpserver.config.Configuration;
import com.basichttpserver.httpserver.config.ConfigurationManager;

/**
 *
 * Driver class for the http server
 */
public class HttpServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Server Starting");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Running on port: " + conf.getPort());
        System.out.println("Running on webRoot: " + conf.getWebroot());
    }
}
