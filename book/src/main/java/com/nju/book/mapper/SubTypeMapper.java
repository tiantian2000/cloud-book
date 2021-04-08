package com.nju.book.mapper;

import com.nju.book.model.SubType;

import java.util.List;

public interface SubTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubType record);

    SubType selectByPrimaryKey(Integer id);

    List<SubType> selectAll();

    int updateByPrimaryKey(SubType record);
}