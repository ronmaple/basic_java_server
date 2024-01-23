package com.basichttpserver.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);
    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = "<html><head><title>Simple Java HTTP Server</title><body><h1>This page was served using java</h1></body></head></html>";
            final String CRLF = "\n\r";
            String response =
                    "HTTP/1.1 200 OK" + CRLF + "Content-Length: " +
                            html.getBytes().length + CRLF +
                            CRLF +
                            html +
                            CRLF + CRLF;
            outputStream.write(response.getBytes());
            LOGGER.info("Connection processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with communication", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }


        }
    }
}
