package airat.valiev.controller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyServer {

    static Logger logger = LoggerFactory.getLogger(JettyServer.class);

    public static void startServer() {

        try {

            Server server = new Server(8080);

            // The ServletHandler is a dead simple way to create a context handler
            // that is backed by an instance of a Servlet.
            // This handler then needs to be registered with the Server object.


            ServletHandler handler = new ServletHandler();
            server.setHandler(handler);

            // Passing in the class for the Servlet allows jetty to instantiate an
            // instance of that Servlet and mount it on a given context path.

            // IMPORTANT:
            // This is a raw Servlet, not a Servlet that has been configured
            // through a web.xml @WebServlet annotation, or anything similar.
            handler.addServletWithMapping(DispatcherServlet.class, "/*");

            // Start things up!
            server.start();

            // The use of server.join() the will make the current thread join and
            // wait until the server is done executing.
            // See
            // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
            server.join();

        } catch (InterruptedException ex) {
            logger.error("Server thread is interrupted");
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
    }

}
