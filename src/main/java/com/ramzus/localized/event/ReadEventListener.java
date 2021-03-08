package com.ramzus.localized.event;

import com.ramzus.localized.Localized;
import com.ramzus.localized.LocalizedIntegrator;
import com.ramzus.localized.LocalizedProperty;
import com.ramzus.localized.exception.LocalizedException;
import org.hibernate.StatelessSession;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;

import java.lang.reflect.Field;

/**
 * Replaces the entity's @{@link Localized} fields after loading.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class ReadEventListener extends AbstractEventListener implements PostLoadEventListener {
    private static final long serialVersionUID = 3078160043184360006L;

    public ReadEventListener(LocalizedIntegrator integrator, SessionFactoryImplementor sessionFactory) {
        super(integrator, sessionFactory);
    }

    @Override
    public void onPostLoad(PostLoadEvent event) {
        handleFields(event, event.getEntity(), event.getId());
    }

    @Override
    protected void handleField(StatelessSession session, Field field, Object entity, LocalizedProperty property) throws LocalizedException {
        try {
            if (property.getValue() != null) {
                field.set(entity, property.getValue());
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new LocalizedException(e);
        }
    }
}
