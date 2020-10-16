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
        final HttpSession session = request.getSession();

        if (CheckSession.check(session, request)) {
            //response.sendRedirect(request.getContextPath());
            response.sendRedirect("/fm/");
        } else {
            final String username = req.getParameter("text");
            final char[] password = req.getParameter("password1").toCharArray();

            final UserDao userDao = (UserDao) request.getServletContext().getAttribute("userDao");
            final int cost = (int) request.getServletContext().getAttribute("cost");

            User user = userDao.getUserByUsername(username);
            PasswordAuthentication passwordAuthentication = new PasswordAuthentication(cost);

            if (user != null && passwordAuthentication.authenticate(password, user.getPassword())) {
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