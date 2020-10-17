package filter;

import exception.CouldntAddData;
import exception.DataIsEmpty;
import exception.UsersPasswordIsNull;
import service.UserService;
import useful.CheckSession;
import useful.LogIn;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

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
        final char[] password1 = req.getParameter("password").toCharArray();
        final char[] password2 = req.getParameter("repeat_password").toCharArray();
        final String email = req.getParameter("email");

        if (!Arrays.equals(password1, password2)) {
            response.sendRedirect("register");
        } else {
            final HttpSession session = request.getSession();

            if (CheckSession.check(session, request)) {
                response.sendRedirect(request.getContextPath());
            } else {
                UserService userService = new UserService();
                if (password1.length >= 8 && password1.length <= 32 && !userService.isUserExist(username)) {
                    try {
                        userService.enrollUser(username, password1, email);
                    } catch (DataIsEmpty | CouldntAddData dataIsEmpty) {
                        dataIsEmpty.printStackTrace();
                    }
                    LogIn.logIn(username, req, request, response);
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