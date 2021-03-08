package com.ramzus.localized;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Locale;

/**
 * Entity for storing @{@link Localized} fields of an arbitrary entity.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"tableName", "instance", "locale", "field"}))
@DynamicInsert
@DynamicUpdate
public class LocalizedProperty implements Serializable {
    private static final long serialVersionUID = -7994792168226645324L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tableName;
    private String instance;
    private Locale locale;
    private String field;
    private String value;

    public LocalizedProperty() {
    }

    public LocalizedProperty(Long id, String tableName, String instance, Locale locale, String field, String value) {
        this.id = id;
        this.tableName = tableName;
        this.instance = instance;
        this.locale = locale;
        this.field = field;
        this.value = value;
    }

    public LocalizedProperty(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalizedProperty setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public LocalizedProperty setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getInstance() {
        return instance;
    }

    public LocalizedProperty setInstance(String instance) {
        this.instance = instance;
        return this;
    }

    public Locale getLocale() {
        return locale;
    }

    public LocalizedProperty setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public String getField() {
        return field;
    }

    public LocalizedProperty setField(String field) {
        this.field = field;
        return this;
    }

    public String getValue() {
        return value;
    }

    public LocalizedProperty setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return String.format("locale=%s, id=%s, %s.%s='%s'", locale, instance, tableName, field, value);
    }
}
