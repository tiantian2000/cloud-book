package com.nju.book.mapper;

import com.nju.book.model.Book;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll(Book book);

    int updateByPrimaryKey(Book record);
}