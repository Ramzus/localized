package com.ramzus.localized.cases;

import org.hibernate.Session;
import org.junit.Test;
import com.ramzus.localized.exception.UnresolvedLocaleException;
import com.ramzus.localized.locale_resolver.ThreadLocalLocaleResolver;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class TestThreadLocalResolver {

    private Session session;

    @Test(expected = UnresolvedLocaleException.class)
    public void testUnsetLocale() throws Exception {
        ThreadLocalLocaleResolver resolver = new ThreadLocalLocaleResolver();
        resolver.resolveLocale(session);
    }

    @Test
    public void testChangeLocale() throws Exception {
        ThreadLocalLocaleResolver resolver = new ThreadLocalLocaleResolver();

        resolver.setLocale(Locale.ENGLISH);
        assertThat(resolver.resolveLocale(session)).isEqualTo(Locale.ENGLISH);

        resolver.setLocale(Locale.GERMAN);
        assertThat(resolver.resolveLocale(session)).isEqualTo(Locale.GERMAN);
    }
}
