package com.oleksandr.librarymanagementsystem.models;

public class BookDecorator implements Readable {

    protected Readable readableBook;

    public BookDecorator(Readable readableBook) {
        this.readableBook = readableBook;
    }

    @Override
    public void read(){
        System.out.println("The book is read online.");
    }
}
