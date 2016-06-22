package jee6.blueprint.servlet.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jee6.blueprint.servlet.header.ServletHeaderUtil;

public class BasicAuthFilter implements Filter {

	private Logger log = LoggerFactory.getLogger(BasicAuthFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		log.debug("doFilter");

		String loggedInUser = httpRequest.getRemoteUser();
		if (loggedInUser != null) {
			// already logged in
			log.debug("User {} already logged in!", loggedInUser);
			chain.doFilter(httpRequest, httpResponse);
			return;
		}

		final String[] credentials = ServletHeaderUtil
				.getBasicAuthCredentials(httpRequest);
		if (credentials != null) {

			String user = credentials[0];
			String pw = credentials[1];

			try {
				httpRequest.login(user, pw);
				log.debug("Login of user {} successful!", user);
			} catch (Throwable t) {
				log.error(t.getMessage(), t);
				handleUnauthorized(httpRequest, httpResponse, chain);
				return;
			}
		} else {
			log.debug("No Credentials in request found!");
		}
		// no login -> guest
		chain.doFilter(httpRequest, httpResponse);
	}

	protected void handleUnauthorized(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain) throws IOException {
		if (req.getAuthType() != HttpServletRequest.BASIC_AUTH) {
			// res.setHeader("WWW-Authenticate", "Basic realm=\"" + REALM +
			// "\"");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"You don't have permissions to view this page.");
			res.flushBuffer();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}