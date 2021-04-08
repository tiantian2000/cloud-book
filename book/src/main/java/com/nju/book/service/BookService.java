package com.nju.book.service;

import com.github.pagehelper.PageInfo;
import com.nju.book.model.Book;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface BookService {

    PageInfo list(Integer currPage, Book book);

    void add(Book book);

    void del(Integer id);

    Book selById(Integer id);

    Book update(Book book);

    PageInfo indexList(Integer currPage);

    List<Book> selAll();

    /**
     * 导出excel
     * @param data
     * @return
     */
    ResponseEntity<byte[]> exportExcel(List<Book> data) throws IOException;
}
