package com.nju.book.mapper;

import com.nju.book.model.MainType;

import java.util.List;

public interface MainTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MainType record);

    MainType selectByPrimaryKey(Integer id);

    List<MainType> selectAll();

    int updateByPrimaryKey(MainType record);
}