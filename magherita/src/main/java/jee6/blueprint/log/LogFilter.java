package jee6.blueprint.log;

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
import org.slf4j.MDC;

public class LogFilter implements Filter {
	private static final String UNAUTH = "unauth";
	public static final String MDC_USER = "USER";
	public static final String MDC_REQUEST_ID = "REQUEST_ID";

	private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

	public LogFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest rq = (HttpServletRequest) request;

		long duration = System.currentTimeMillis();
		String target = rq.getRequestURI();

		String name = rq.getRemoteUser();
		if (name == null) {
			name = UNAUTH;
		}
		
		initMDCVariables(name);

		log.info("Executing {} ...", target); // makes it possible to spot hung requests
		
		try {
			chain.doFilter(request, response);
		} finally {
			int status = res.getStatus();
			duration = System.currentTimeMillis() - duration;
			log.info("Executed {} Duration {} Status {}", target, duration, status);
	
			clearMDCVariables();
		}
	}
	
	public static void initMDCVariables(String name) {
		MDC.put(MDC_USER, name);
		MDC.put(MDC_REQUEST_ID, java.util.UUID.randomUUID().toString());
	}

	public static void clearMDCVariables() {
		MDC.clear();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
