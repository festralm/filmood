package filters;

import models.CheckSession;
import models.LogIn;
import models.User;
import models.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
    private static Logger logger = Logger.getLogger(RegistrationFilter.class.getName());
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse resp,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final String login = req.getParameter("text");
        final String email = req.getParameter("email");
        final String password1 = req.getParameter("password1");
        final String password2 = req.getParameter("password2");

        if (!password1.equals(password2)) {
            response.sendRedirect("register");
        }
        else {
            @SuppressWarnings("unchecked") final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) request.getServletContext().getAttribute("dao");

            final HttpSession session = request.getSession();

            if (CheckSession.check(session, request)) {
                response.sendRedirect(request.getContextPath());
            } else {
                if (!dao.get().isLoginExist(login)) {
                    dao.get().add(new User(dao.get().getStoreSize(), login, email, password1));
                    LogIn.logIn(login, password1, req, resp, request, response);
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