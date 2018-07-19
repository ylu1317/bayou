package edu.rice.pliny.apitrans.examples;

import com.sun.net.httpserver.HttpExchange;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import static org.junit.Assert.assertEquals;

public class HTTPServer {
    String url = "/test";
    int port = 8000;
    static String content = "foobar";

    static class MyHandler implements com.sun.net.httpserver.HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            t.sendResponseHeaders(200, content.length());
            OutputStream os = t.getResponseBody();
            os.write(content.getBytes());
            os.close();
        }
    }

    static class MyHandler2 extends HttpHandler {
        @Override
        public void service(Request request, Response response) throws Exception {
            response.setContentType("text/plain");
            response.setContentLength(content.length());
            response.getWriter().write(content);
        }
    }

    @Test
    public void read_text() throws IOException {
        InetSocketAddress address = new InetSocketAddress(port);
        com.sun.net.httpserver.HttpServer server = com.sun.net.httpserver.HttpServer.create(address, 0);
        MyHandler handler = new MyHandler();
        server.createContext(url, handler);
        server.start();
    }

    @Test
    public void read_text2() throws Exception {
        HttpServer server = HttpServer.createSimpleServer();
        ServerConfiguration config = server.getServerConfiguration();
        MyHandler2 handler2 = new MyHandler2();
        config.addHttpHandler(handler2);
        server.start();
    }

}
