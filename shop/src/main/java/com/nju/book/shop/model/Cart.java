package com.nju.book.shop.model;

import model.Book;

public class Cart {
    private Integer id;

    private Integer userId;

    private Integer bookId;

    private Integer num;

    //一对一关联
    private Book book;

    /*public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}