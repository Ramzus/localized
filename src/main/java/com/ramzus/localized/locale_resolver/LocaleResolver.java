package com.ramzus.localized.locale_resolver;

import com.ramzus.localized.LocalizedIntegrator;
import com.ramzus.localized.exception.UnresolvedLocaleException;
import org.hibernate.Session;

import java.util.Locale;

/**
 * Resolves the locale based on the {@link Session}.
 *
 * Register a resolver at {@link LocalizedIntegrator#setLocaleResolver(LocaleResolver)}
 * or by Hibernate's property {@link LocalizedIntegrator#LOCALE_RESOLVER}.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public interface LocaleResolver {

    Locale resolveLocale(Session session) throws UnresolvedLocaleException;
}
