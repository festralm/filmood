package filters;

import models.CheckSession;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MainFilter")
public class MainFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
            throws ServletException, IOException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;
        final HttpSession session = request.getSession();
        if (CheckSession.check(session, request)) {
            req.getRequestDispatcher("html/temp_main_page_authorized.html").forward(req, resp);
        } else {
            req.getRequestDispatcher("html/temp_main_page_non_authorized.html").forward(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
