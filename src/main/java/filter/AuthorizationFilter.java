package filter;

import dao.*;
import dto.User;
import useful.CheckSession;
import useful.LogIn;
import useful.PasswordAuthentication;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse resp,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final String username = req.getParameter("text");
        final char[] password = req.getParameter("password1").toCharArray();

        final UserDao userDao = (UserDao) request.getServletContext().getAttribute("userDao");
        final byte[] salt = (byte[]) request.getServletContext().getAttribute("salt");

        final HttpSession session = request.getSession();

        if (CheckSession.check(session, request)) {
            response.sendRedirect(request.getContextPath());
        } else {
            User user = userDao.getUserByUsername(username);
            //if (userDao.isUserExist(username, passwordHash)) {
            if (user != null && PasswordAuthentication.verifyPassword(password, user.getPassword(), salt)) {
                LogIn.logIn(username, user.getPassword(), req, resp, request, response);
            } else {
                request.getSession().setAttribute("check_password", false);
                response.sendRedirect("authorize");
                //req.getRequestDispatcher("/authorize").forward(req, resp);
            }
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}