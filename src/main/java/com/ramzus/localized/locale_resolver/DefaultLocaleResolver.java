package com.ramzus.localized.locale_resolver;

import com.ramzus.localized.exception.UnresolvedLocaleException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

/**
 * Returns the VM's default locale.
 *
 * This is the default locale resolver.
 *
 * @see Locale#getDefault()
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class DefaultLocaleResolver implements LocaleResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultLocaleResolver.class);

    private boolean warnOnce;

    public boolean isWarnOnce() {
        return warnOnce;
    }

    public DefaultLocaleResolver setWarnOnce(boolean warnOnce) {
        this.warnOnce = warnOnce;
        return this;
    }

    @Override
    public Locale resolveLocale(Session session) throws UnresolvedLocaleException {
        if (warnOnce) {
            warnOnce = false;
            LOGGER.warn("You didn't configure a LocaleResolver for @Localized. As default the locale resolves now to the VM's locale.");
        }
        return Locale.getDefault();
    }
}
