package filters;

import interfaces.DataAccessable;
import models.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@WebFilter(filterName = "AuthorizationFilter")
public class AuthorizationFilter implements Filter {
    private static Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse resp,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        @SuppressWarnings("unchecked")
        final AtomicReference<DataAccessable> dao = (AtomicReference<DataAccessable>) request.getServletContext().getAttribute("dao");

        final HttpSession session = request.getSession();

        if (CheckSession.check(session, request)) {
            response.sendRedirect(request.getContextPath());
        }
        else {
            if (dao.get().isUserExist(login, password.hashCode())) {
                LogIn.logIn(login, password, req, resp, request, response);
            } else {
                request.getSession().setAttribute("check_password", false);
                req.getRequestDispatcher("jsp/temp_authorization.jsp").forward(req, resp);
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