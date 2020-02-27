package com.oleksandr.librarymanagementsystem.models;

public class BlackCopier extends Copier {
    @Override
    protected void doCopy(Book book) {
        System.out.println(String.format("Copying book %s on the BLACK copier.", book.name));
    }
}
