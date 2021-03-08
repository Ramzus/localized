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
public class TestLoad {

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
    }

    @Test
    public void testNull() throws Exception {
        localeResolver.setLocale(Locale.US);

        Book book = new Book()
                .setAuthor("Author");
        session.save(book);
        session.flush();

        session.refresh(book);
        assertThat(book.getTitle()).isNull();

        localeResolver.setLocale(Locale.GERMAN);
        session.refresh(book);
        assertThat(book.getTitle()).isNull();
    }

    @Test
    public void testDefaultTranslation() throws Exception {
        localeResolver.setLocale(Locale.US);

        Book book = new Book()
                .setAuthor("Author")
                .setTitle("Title");
        session.save(book);
        session.flush();

        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");

        localeResolver.setLocale(Locale.GERMAN);
        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");

        localeResolver.setLocale(Locale.US);
        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");
    }

    @Test
    public void testTranslated() throws Exception {
        localeResolver.setLocale(Locale.US);

        Book book = new Book()
                .setAuthor("Author")
                .setTitle("Title");
        session.save(book);
        session.flush();

        localeResolver.setLocale(Locale.GERMAN);
        book.setTitle("Titel");
        session.save(book);
        session.flush();

        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Titel");

        localeResolver.setLocale(Locale.US);
        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");

        localeResolver.setLocale(Locale.GERMAN);
        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Titel");
    }

    @Test
    public void testFallBackToLanguage() throws Exception {
        localeResolver.setLocale(Locale.ENGLISH);

        Book book = new Book()
                .setAuthor("Author")
                .setTitle("Title");
        session.save(book);
        session.flush();

        localeResolver.setLocale(Locale.US);
        session.refresh(book);
        assertThat(book.getTitle()).isEqualTo("Title");
    }
}
