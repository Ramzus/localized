package com.ramzus.localized.rule;

import com.ramzus.localized.repository.LocalizedSessionRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import com.ramzus.localized.LocalizedIntegrator;
import com.ramzus.localized.LocalizedProperty;
import com.ramzus.localized.locale_resolver.ThreadLocalLocaleResolver;
import com.ramzus.localized.model.Book;

/**
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class SessionRule implements MethodRule {

    public static final String TABLE_NAME = "Book";

    private Session session;
    private SessionFactory sessionFactory;
    private LocalizedSessionRepository repository;
    private ThreadLocalLocaleResolver localeResolver;
    private boolean initResolver;

    public SessionRule() {
        this(true);
    }

    public SessionRule(boolean initResolver) {
        this.initResolver = initResolver;
    }

    public Session getSession() {
        return session;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public LocalizedSessionRepository getRepository() {
        return repository;
    }

    public ThreadLocalLocaleResolver getLocaleResolver() {
        return localeResolver;
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                session = sessionFactory.openSession();
                session.beginTransaction();
                try {
                    repository = new LocalizedSessionRepository(session);
                    localeResolver = new ThreadLocalLocaleResolver();
                    if (initResolver) {
                        LocalizedIntegrator.setLocaleResolver(localeResolver);
                    }
                    base.evaluate();
                } finally {
                    session.close();
                    sessionFactory.close();
                }
            }
        };
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(LocalizedProperty.class);
        return configuration.buildSessionFactory();
    }
}
