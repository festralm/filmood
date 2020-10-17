package filter;

import dto.User;
import service.UserService;
import useful.CheckSession;
import useful.LogIn;

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

            throws IOException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;
        final HttpSession session = request.getSession();

        if (CheckSession.check(session, request)) {
            response.sendRedirect("/fm/");
        } else {
            final String username = req.getParameter("text");
            final char[] password = req.getParameter("password1").toCharArray();

            UserService userService = new UserService();

            if (userService.authenticateUser(username, password)) {
                LogIn.logIn(username, req, request, response);
            } else {
                request.getSession().setAttribute("check_password", false);
                response.sendRedirect("authorize");
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