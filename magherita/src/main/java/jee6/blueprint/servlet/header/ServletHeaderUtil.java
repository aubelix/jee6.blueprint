package jee6.blueprint.servlet.header;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

public class ServletHeaderUtil {
	private static final String SEPERATOR = ":";
	private static final String UTF_8 = "UTF-8";
	private static final String AUTHORIZATION = "Authorization";

	public static String[] getBasicAuthCredentials(HttpServletRequest httpRequest) {
		final String auth = httpRequest.getHeader(AUTHORIZATION);
		if (auth == null) {
			return null;
		}

		final int index = auth.indexOf(' ');
		if (index < 0) {
			return null;
		}
		
		try {
			String authString = auth.substring(index);
			byte[] decoded = DatatypeConverter.parseBase64Binary(authString);
			String credString = new String(decoded, UTF_8);
			final String[] credentials = credString.split(SEPERATOR);
			
			if(credentials.length != 2) {
				return null;
			}
			
			return credentials;
		} catch (Exception e) {
			// log.warn();	// swallow. bad logins fill up logs
		}
		
		return null;
	}

}
