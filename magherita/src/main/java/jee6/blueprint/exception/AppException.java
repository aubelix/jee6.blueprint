package jee6.blueprint.exception;

import java.util.Locale;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import jee6.blueprint.bundle.BundleKey;
import jee6.blueprint.bundle.TextBundle;

/**
 * Should be thrown when Error Message will be translated and sent back to the
 * client
 * 
 * @author max_kuffs
 * 
 */
@ApplicationException(rollback = true)
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 767743867836452484L;

	protected static final Status DEFAULT_STATUS = Status.BAD_REQUEST;
	
	protected Status status = Status.BAD_REQUEST;
	protected String errorCode;
	protected Object parameter1;
	protected Object parameter2;

	public AppException(BundleKey errorCode, Response.Status status,
			Object parameter1) {
		this(errorCode.toString(), status, parameter1, null);
	}

	public AppException(BundleKey errorCode, Object param1, Object param2) {
		this(errorCode.toString(), DEFAULT_STATUS, param1, param2);
	}

	public AppException(BundleKey errorCode, Object param1) {
		this(errorCode.toString(), DEFAULT_STATUS, param1, null);
	}

	public AppException(BundleKey errorCode, Response.Status status) {
		this(errorCode.toString(), status, null, null);
	}

	public AppException(BundleKey errorCode) {
		this(errorCode.toString(), DEFAULT_STATUS, null, null);
	}
	
	private AppException(String errorCode, Response.Status status,
			Object parameter1, Object parameter2) {
		super(errorCode);
		
		if(errorCode == null) {
			throw new IllegalArgumentException("Errorcode required!");
		}
		
		this.errorCode = errorCode;
		
		if(status != null) {
			this.status = status;
		}
		
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
	}

	public Status getStatus() {
		return status;
	}

	public String getLocalizedMessage(Locale lang) {
		return TextBundle.INSTANCE.getText(lang, errorCode, parameter1, parameter2);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object getParameter1() {
		return parameter1;
	}

	public Object getParameter2() {
		return parameter2;
	}

	public String getTechnicalMessage() {
		StringBuilder sb = new StringBuilder(errorCode);
		if (parameter1 != null) {
			sb.append(", ").append(parameter1);
		}

		if (parameter2 != null) {
			sb.append(", ").append(parameter2);
		}
		return sb.toString();
	}
}
