package com.oleksandr.librarymanagementsystem.models;

public class BookDecorator implements Readable {

    protected final Readable readableBook;
    protected User reader;

    public BookDecorator(Readable readableBook) {
        this.readableBook = readableBook;
        this.reader = readableBook.getReader();
    }

    @Override
    public void read(){
        System.out.println(String.format("The book %s is read online by: %s %s", this.getName(), this.getReader().getFirstName(), this.getReader().getLastName()));
    }

    @Override
    public User getReader() {
        return reader;
    }

    @Override
    public void setReader(User reader) {
        this.reader = reader;
    }

    @Override
    public String getName() {
        return readableBook.getName();
    }
}
