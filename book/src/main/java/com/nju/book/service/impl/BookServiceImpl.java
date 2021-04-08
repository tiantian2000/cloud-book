package com.nju.book.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nju.book.mapper.BookMapper;
import com.nju.book.model.Book;
import com.nju.book.service.BookService;
import com.nju.book.util.Define;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookMapper bookMapper;

    /**
     * 分页查询
     * @param currPage
     * @return
     */
    @Override
    public PageInfo list(Integer currPage,Book book) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage, Define.ADMIN_BOOK_PAGE_SIZE);
        return new PageInfo<>(bookMapper.selectAll(book));
    }

    @Override
    public void add(Book book) {
        book.setNewBook("1");
        book.setBdate(new Date());
        bookMapper.insert(book);
    }

    /**
     * CacheEvict:删除,从缓存中删除相关的key为id的数据
     * @param id
     */
    @Override
    @CacheEvict(value="book",key="#id")
    public void del(Integer id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    /**
     *  Cacheable: 如果缓存中没有值,则执行方法的内容并且缓存到redis,
     *  如果有值则不执行方法内容,直接从redis中取
     *  根据ID从redis中取,如果没有,从mysql中取,再缓存到redis
     *  value: 缓存位置的名称,不能为空
     *  key: 缓存的key,默认为空
     *  unless: 函数返回值符合表达式,则否决(不缓存)
     * @param id
     * @return
     */
    @Override
    @Cacheable(value="book",key="#id",unless = "#result==null")
    public Book selById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    /**
     * CachePut: 保存修改方法被调用后,返回结果重新缓存
     * @param book
     */
    @Override
    @CachePut(value="book",key="#book.id")
    public Book update(Book book) {
        bookMapper.updateByPrimaryKey(book);
        return book;
    }

    @Override
    public PageInfo indexList(Integer currPage) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage, Define.INDEX_BOOK_PAGE_SIZE);
        return new PageInfo<>(bookMapper.selectAll(null));
    }

    @Override
    public List<Book> selAll() {
        return bookMapper.selectAll(null);
    }

    @Override
    public ResponseEntity<byte[]> exportExcel(List<Book> data) throws IOException{
        //创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置文档属性
        workbook.createInformationProperties();
        //创建一个sheet
        HSSFSheet sheet = workbook.createSheet();
        //创建第一行,做为标题行
        HSSFRow row = sheet.createRow(0);
        //往行中添加列(单元格)
        HSSFCell cell0 = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        HSSFCell cell3 = row.createCell(3);
        HSSFCell cell4 = row.createCell(4);
        HSSFCell cell5 = row.createCell(5);
        //设置单元格的值
        cell0.setCellValue("ISBN");
        cell1.setCellValue("名称");
        cell2.setCellValue("现价");
        cell3.setCellValue("作者");
        cell4.setCellValue("出版社");
        cell5.setCellValue("出版日期");

        //创建单元格的样式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //创建日期格式的样式
        short format = HSSFDataFormat.getBuiltinFormat("m/d/yy");
        //为样式对象设置日期格式
        cellStyle.setDataFormat(format);

        //遍历需要添加的数据
        for(int i=0;i<data.size();i++){
            //取出图书
            Book book = data.get(i);
            //创建新的一行
            HSSFRow hssfRow = sheet.createRow(i+1);
            //往行中添加列(单元格)
            HSSFCell c0 = hssfRow.createCell(0);
            HSSFCell c1 = hssfRow.createCell(1);
            HSSFCell c2 = hssfRow.createCell(2);
            HSSFCell c3 = hssfRow.createCell(3);
            HSSFCell c4 = hssfRow.createCell(4);
            HSSFCell c5 = hssfRow.createCell(5);
            //填写数据
            c0.setCellValue(book.getIsbn());
            c1.setCellValue(book.getBname());
            c2.setCellValue(book.getCurPrice());
            c3.setCellValue(book.getAuthor());
            c4.setCellValue(book.getPublisher());
            //指定单元格样式
            c5.setCellStyle(cellStyle);
            c5.setCellValue(book.getPubDate());
        }
        //创建一个Http请求头
        HttpHeaders headers = new HttpHeaders();
        //设置参数 第一个参数:控制方式:内嵌 第二个参数:文件名(中文得转码)
        headers.setContentDispositionFormData("attachment",
                new String("图书.xlsx".getBytes("UTF-8"),"iso-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //创建一个字节数组输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //把导出的excel文件内容写个字节数组输出流
        workbook.write(bos);
        ResponseEntity<byte[]> responseEntity
                = new ResponseEntity<>(bos.toByteArray(),headers, HttpStatus.CREATED);
        return responseEntity;
    }
}
