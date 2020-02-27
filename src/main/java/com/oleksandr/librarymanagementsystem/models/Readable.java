package com.oleksandr.librarymanagementsystem.models;

public interface Readable {
    void read();

    User getReader();

    String getName();

    void setReader(User reader);
}
