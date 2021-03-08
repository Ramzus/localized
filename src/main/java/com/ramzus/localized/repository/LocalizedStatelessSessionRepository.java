package com.ramzus.localized.repository;

import org.hibernate.StatelessSession;

/**
 * Repository for backed by a {@link StatelessSession}.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class LocalizedStatelessSessionRepository extends LocalizedRepository<StatelessSession> {

    public LocalizedStatelessSessionRepository(StatelessSession session) {
        super(session);
    }

}
