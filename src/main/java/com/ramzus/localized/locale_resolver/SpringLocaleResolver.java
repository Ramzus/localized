package com.ramzus.localized.locale_resolver;

import com.ramzus.localized.exception.UnresolvedLocaleException;
import org.hibernate.Session;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * Resolve locale for a Spring/JPA environment.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class SpringLocaleResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(Session session) throws UnresolvedLocaleException {
        return LocaleContextHolder.getLocale();
    }
}
