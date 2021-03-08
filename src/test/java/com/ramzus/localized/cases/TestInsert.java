package com.ramzus.localized.cases;

import com.ramzus.localized.locale_resolver.ThreadLocalLocaleResolver;
import com.ramzus.localized.model.Book;
import com.ramzus.localized.rule.SessionRule;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class TestInsert {

    private Session session;
    private ThreadLocalLocaleResolver localeResolver;

    @Rule
    public final SessionRule sessionRule = new SessionRule();

    @Before
    public void session() {
        session = sessionRule.getSession();
    }

    @Before
    public void localizedConfiguration() {
        localeResolver = sessionRule.getLocaleResolver();
        localeResolver.setLocale(Locale.US);
    }

    @Test
    public void testInsertNull() throws Exception {
        Book book = new Book()
                .setAuthor("Author");
        session.save(book);
        session.flush();

        session.refresh(book);
        assertThat(book.getTitle()).isNull();
    }

    @Test
    public void testInsert() throws Exception {
        Book book = new Book()
                .setAuthor("Author")
                .setTitle("Title");
        session.save(book);
        session.flush();

        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");
    }
}
