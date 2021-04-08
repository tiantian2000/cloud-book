package com.nju.book.service.impl;

import com.nju.book.mapper.MainTypeMapper;
import com.nju.book.mapper.SubTypeMapper;
import com.nju.book.model.MainType;
import com.nju.book.model.SubType;
import com.nju.book.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private MainTypeMapper mainTypeMapper;

    @Autowired
    private SubTypeMapper subTypeMapper;

    @Override
    public List<MainType> selMainType() {
        return mainTypeMapper.selectAll();
    }

    @Override
    public List<SubType> selSubType() {
        return subTypeMapper.selectAll();
    }
}
