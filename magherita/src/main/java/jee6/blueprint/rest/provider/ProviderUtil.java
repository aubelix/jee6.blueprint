package jee6.blueprint.rest.provider;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ProviderUtil {
	public static boolean isSupportingXmlJson(HttpHeaders headers) {
		List<MediaType> mediaTypes = headers.getAcceptableMediaTypes();
		
		return mediaTypes.contains(MediaType.APPLICATION_XML) || mediaTypes.contains(MediaType.APPLICATION_JSON_TYPE);
	}
}
