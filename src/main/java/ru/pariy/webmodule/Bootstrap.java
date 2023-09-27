package ru.pariy.webmodule;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.*;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import ru.pariy.webmodule.api.http.XmlParserServlet;
import ru.pariy.webmodule.utils.PropertyUtils;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Bootstrap {
    public static void main(String[] args) throws Exception {
        int port = PropertyUtils.getPropertiesPort(PropertyUtils.getPropertiesPath());
        URL url = Bootstrap.class.getClassLoader().getResource("web");
        PathResource pathResource = new PathResource(url);
        Server server = createServer(port, pathResource);

        server.start();
        server.join();
    }

    public static Server createServer(int port, Resource baseResource) {
        Server server = new Server(port);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});
        resourceHandler.setBaseResource(baseResource);

        // Create XmlParserServlet handler
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(XmlParserServlet.class, "/api/get-xml");

        // Add the ResourceHandler to the server.
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler, handler});

        server.setHandler(handlers);
        return server;
    }


}
