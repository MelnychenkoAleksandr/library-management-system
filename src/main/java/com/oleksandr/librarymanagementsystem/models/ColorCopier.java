package com.oleksandr.librarymanagementsystem.models;

public class ColorCopier extends Copier {
    @Override
    protected void doCopy(Book book) {
        System.out.println(String.format("Copying book %s on the COLOR copier.", book.name));
    }
}
