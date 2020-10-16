import dao.*;
import dto.*;
import lombok.SneakyThrows;
import useful.PasswordAuthentication;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Scanner;

@WebListener()
public class ContextListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private UserDao userDao = new UserDaoMySql();
    private int cost = 15;

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

//        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(cost);
//
//        User alia = new User();
//        alia.setUsername("alia");
//        alia.setPassword(passwordAuthentication.hashPassword("alia".toCharArray()));
//        alia.setEmail("alia@gmail.com");
//        alia.setBirthdate(new Date(2000, 1, 25));
//        alia.setFullname("Миннегараева Алия Рустемовна");
//
//
//        User chulpan = new User();
//        chulpan.setUsername("chulpan");
//        chulpan.setPassword(passwordAuthentication.hashPassword("chulpan".toCharArray()));
//        chulpan.setEmail("chulpan@gmail.com");
//        chulpan.setBirthdate(new Date(2001, 1, 1));
//        chulpan.setFullname("Хайруллина  Чулпан Маратовна");
//
//        userDao.addUser(alia);
//        userDao.addUser(chulpan);

        final ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("userDao", userDao);
        servletContext.setAttribute("cost", cost);
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
        userDao = null;
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
