package com.ramzus.localized.exception;

import com.ramzus.localized.locale_resolver.LocaleResolver;

/**
 * Exception during locale resolution.
 *
 * @see LocaleResolver#resolveLocale(org.hibernate.Session)
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class UnresolvedLocaleException extends LocalizedException {
    private static final long serialVersionUID = 7436361288749125603L;

    public UnresolvedLocaleException(String message) {
        super(message);
    }

    public UnresolvedLocaleException(Throwable cause) {
        super(cause);
    }

    public UnresolvedLocaleException(String message, Throwable cause) {
        super(message, cause);
    }
}
