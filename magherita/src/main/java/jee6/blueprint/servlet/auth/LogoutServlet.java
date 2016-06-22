package jee6.blueprint.servlet.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by mike on 12.08.2014.
 */
@WebServlet(name = "Logout", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -7453645631251203194L;
	public static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("Referrer: {}", request.getHeader("Referer"));
        logger.debug("Principal = {}", request.getUserPrincipal());
        // always get a session! because firefox needs the server to delete the cookie
        HttpSession session = request.getSession(true);
        logger.debug("session.invalidate(); SessionId = {};", session.getId());
        session.invalidate();
        request.logout();
        logger.debug("Principal = {}", request.getUserPrincipal());
        response.sendRedirect("dummy/ok.html");
    }
}