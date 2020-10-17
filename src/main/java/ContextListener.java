import exception.CouldntAddData;
import exception.DataIsEmpty;
import service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    // Public constructor is required by servlet spec
    public ContextListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------


    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */
        UserService aliaService = new UserService();

        if (!aliaService.isUserExist("alia")) {
            try {
                aliaService.enrollUser("alia", "alia".toCharArray(), "alia@gmail.com");
            } catch (DataIsEmpty | CouldntAddData dataIsEmpty) {
                dataIsEmpty.printStackTrace();
            }
        }

        UserService chulpanService = new UserService();
        if (!chulpanService.isUserExist("chulpan")) {
            try {
                chulpanService.enrollUser("chulpan", "chulpan".toCharArray(), "chulpan@gmail.com");
            } catch (DataIsEmpty | CouldntAddData dataIsEmpty) {
                dataIsEmpty.printStackTrace();
            }
        }

    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attribute
         is replaced in a session.
      */
    }
}
