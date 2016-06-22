package jee6.blueprint.rest.provider;

import java.security.Principal;
import java.util.Locale;
import java.util.Set;

import javax.ejb.EJBAccessException;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jee6.blueprint.bundle.BundleKey;
import jee6.blueprint.bundle.MagheritaBundle;
import jee6.blueprint.dto.ResponseDTO;
import jee6.blueprint.exception.ExceptionUtil;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {
	private Logger log = LoggerFactory.getLogger(EJBExceptionMapper.class);

	@Context
	private HttpHeaders headers;

	@Inject
	private Principal principal;

	@Context
	private HttpServletRequest httpRequest;

	@Override
	public Response toResponse(EJBException exeption) {
		Throwable ex = ExceptionUtil.findRootException(exeption);
		Locale locale = httpRequest.getLocale();

		BundleKey errorCode = null;
		if (ex instanceof ConstraintViolationException) {
			ConstraintViolationException ce = (ConstraintViolationException) ex;

			Set<ConstraintViolation<?>> constraintViolations = ce.getConstraintViolations();
			ResponseDTO entity = MagheritaBundle.INSTANCE.getTexts(locale, constraintViolations);
			Status status = Status.BAD_REQUEST;

			return Response.status(status).entity(entity).build();
		}

		Object entity = null;
		Status status = Status.INTERNAL_SERVER_ERROR;
		if (ex instanceof EJBAccessException) {
			if (isLoggedIn()) {
				status = Status.FORBIDDEN;
				errorCode = BundleKey.FORBIDDEN;
			} else {
				status = Status.UNAUTHORIZED;
				errorCode = BundleKey.UNAUTHORIZED;
			}
		} else if (ex instanceof OptimisticLockException) {
			log.error(ex.getMessage(), ex);
			status = Status.CONFLICT;
			errorCode = BundleKey.RESOURCE_MODYFIED;
		} else {
			log.error(ex.getMessage(), ex);
			errorCode = BundleKey.INTERNAL_ERROR;
		}

		String errorMsg = MagheritaBundle.INSTANCE.getText(locale, errorCode);
		if (ProviderUtil.isSupportingXmlJson(headers)) {
			String errorCodeString = toNullableString(errorCode);
			entity = new ResponseDTO(errorCodeString, errorMsg);
		} else {
			entity = errorMsg;
		}

		ResponseBuilder rb = Response.status(status).entity(entity);
		return rb.build();
	}

	private String toNullableString(BundleKey errorCode) {
		if (errorCode == null) {
			return null;
		}

		return errorCode.toString();
	}

	private boolean isLoggedIn() {
		return "anonymous".equals(principal.getName());
	}
}
