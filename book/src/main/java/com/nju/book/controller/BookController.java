package com.nju.book.controller;

import com.github.pagehelper.PageInfo;
import com.nju.book.model.Book;
import com.nju.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;


    @RequestMapping("indexList/{currPage}")
    public PageInfo<Book> indexList(@PathVariable Integer currPage){
        return bookService.indexList(currPage);
    }

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @RequestMapping("list/{currPage}")
    public PageInfo<Book> list(@PathVariable Integer currPage, @RequestBody Book book){
        return bookService.list(currPage,book);
    }

    /**
     * 新增
     * @param book
     * @return
     */
    @RequestMapping("add")
    public String add(@RequestBody Book book){
        System.out.println(book);
        bookService.add(book);
        return "succ";
    }

    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id){
        bookService.del(id);
        return "succ";
    }

    @RequestMapping("selById/{id}")
    public Book selById(@PathVariable Integer id){
        return bookService.selById(id);
    }

    @RequestMapping("update")
    public String update(@RequestBody Book book){
        bookService.update(book);
        return "succ";
    }

    /**
     * 导出Excel
     * @return
     * @throws IOException
     */
    @RequestMapping("exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException{
       return bookService.exportExcel(bookService.selAll());
    }
}
