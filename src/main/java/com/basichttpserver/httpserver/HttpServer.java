package com.basichttpserver.httpserver;

import com.basichttpserver.httpserver.config.Configuration;
import com.basichttpserver.httpserver.config.ConfigurationManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

        try {
            ServerSocket serverSocket = new ServerSocket(conf.getPort());
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP Server</title><body><h1>This page was served using java</h1></body></head></html>";
            final String CRLF = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF + "Content-Length: " +
                            html.getBytes().length + CRLF +
                            CRLF +
                            html +
                            CRLF + CRLF;
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
