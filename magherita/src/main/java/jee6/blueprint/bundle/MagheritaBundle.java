package jee6.blueprint.bundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;

import jee6.blueprint.dto.ResponseDTO;

public class MagheritaBundle {
	public static final String BUNDLE_NAME = "msg_bundle";
	public static final MagheritaBundle INSTANCE = new MagheritaBundle();
	
	public ResourceBundle getBundle(Locale locale) {
		if (locale == null) {
			locale = Locale.ENGLISH;
		}
		
		// JVM already caches this which is nice
		return ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}

	public String getText(Locale locale, String key) {
		if (locale == null)
			locale = Locale.ENGLISH;

		try {
			ResourceBundle rb = getBundle(locale);
			return rb.getString(key);
		} catch (MissingResourceException e) {
			// try to retrieve text in English
			if (Locale.ENGLISH != locale)
				return getText(Locale.ENGLISH, key);
		}

		// Fallback if nothing found return key!
		return key;
	}

	public String getText(Locale locale, BundleKey key, Object... args) {
		return getText(locale, key.toString(), args);
	}
	
	public String getText(Locale locale, String key, Object... args) {
		return MessageFormat.format(getText(locale, key), args);
	}

	public ResponseDTO getTexts(Locale lang,
			Set<ConstraintViolation<?>> constraintViolations) {
		for (ConstraintViolation<?> cv : constraintViolations) {
			String key = cv.getMessageTemplate();

			String errorMsg = getText(lang, key);
			ResponseDTO res = new ResponseDTO(key, errorMsg);
			return res;
		}
		
		return null;
	}

	public String getText(Locale locale, BundleKey key) {
		return getText(locale, key.toString());
	}
}
