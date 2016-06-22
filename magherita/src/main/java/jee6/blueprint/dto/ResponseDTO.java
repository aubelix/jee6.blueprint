package jee6.blueprint.dto;

public class ResponseDTO {

	private String errorCode;
	private String error;

	public ResponseDTO() {
	}

	public ResponseDTO(String errorCode, String error) {
		super();
		this.errorCode = errorCode;
		this.error = error;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResponseDTO [errorCode=" + errorCode + ", error=" + error + "]";
	}
}
