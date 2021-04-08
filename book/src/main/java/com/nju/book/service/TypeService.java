package com.nju.book.service;

import com.nju.book.model.MainType;
import com.nju.book.model.SubType;

import java.util.List;

public interface TypeService {

    List<MainType> selMainType();

    List<SubType> selSubType();
}
