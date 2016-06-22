package jee6.blueprint.exception;

public class ExceptionUtil {
	public static Throwable findRootException(Throwable ex) {
		Throwable cause = ex.getCause();
		// check per reference is correct to avoid endless loops!
		if (cause == null || cause == ex) {
			return ex;
		}

		return findRootException(cause);
	}
}
