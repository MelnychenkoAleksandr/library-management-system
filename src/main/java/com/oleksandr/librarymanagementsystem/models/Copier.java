package com.oleksandr.librarymanagementsystem.models;

public abstract class Copier {
    public void copy(Book book){
        doCopy(book);
    }

    protected abstract void doCopy(Book book);
}
