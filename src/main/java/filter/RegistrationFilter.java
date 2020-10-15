package filter;

import dao.UserDao;
import useful.CheckSession;
import dto.User;
import useful.LogIn;
import useful.PasswordAuthentication;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse resp,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final String username = req.getParameter("text");
        final char[] password1 = req.getParameter("password1").toCharArray();
        final char[] password2 = req.getParameter("password2").toCharArray();
        final String email = req.getParameter("email");

        if (!Arrays.equals(password1, password2)) {
            response.sendRedirect("register");
        } else {
            final UserDao dao = (UserDao) request.getServletContext().getAttribute("userDao");
            final byte[] salt = (byte[]) request.getServletContext().getAttribute("salt");

            final HttpSession session = request.getSession();

            if (CheckSession.check(session, request)) {
                response.sendRedirect(request.getContextPath());
            } else {
                if (!dao.isUsernameExist(username)) {
                    String passwordHash = PasswordAuthentication.hashPassword(password1, salt);
                    dao.addUser(new User(username, passwordHash, email));
                    LogIn.logIn(username, passwordHash, req, resp, request, response);
                } else {
                    request.getSession().setAttribute("check_login", false);
                    response.sendRedirect("register");
                    //req.getRequestDispatcher("jsp/temp_registration.jsp").forward(req, resp);
                }
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