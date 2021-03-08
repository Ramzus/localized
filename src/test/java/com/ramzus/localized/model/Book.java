package com.ramzus.localized.model;

import com.ramzus.localized.Localized;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String author;
    @Localized
    private String title;

    public Book() {
    }

    public Book(int id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }
}
