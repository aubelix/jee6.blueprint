package jee6.blueprint.rest.provider;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jee6.blueprint.dto.ResponseDTO;
import jee6.blueprint.exception.AppException;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    @Context
    private HttpHeaders headers;
    
    private Logger log = LoggerFactory.getLogger(AppExceptionMapper.class);
    
    @Context
	private HttpServletRequest httpRequest;
    
	@Override
	public Response toResponse(AppException exception) {
		log.error(exception.getTechnicalMessage(), exception);
		
		Status status = exception.getStatus();
		Locale locale = httpRequest.getLocale();
		String errorMsg = exception.getLocalizedMessage(locale);
		String errorCode = exception.getErrorCode();
		Object entity = errorMsg;
		
		if(ProviderUtil.isSupportingXmlJson(headers)) {
			entity = new ResponseDTO(errorCode, errorMsg);
		}
		
		ResponseBuilder rb = Response.status(status).entity(entity);
		return rb.build();
	}

}
