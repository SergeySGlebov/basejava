package com.urise.webapp.model;

import java.util.Objects;

public class Contact {
    private final String description;
    private String link;

    public Contact(String description) {
        this.description = description;
    }

    public Contact(String description, String link) {
        this(description);
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!description.equals(contact.description)) return false;
        return Objects.equals(link, contact.link);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    public void printText() {
        System.out.println(description);
    }
}
