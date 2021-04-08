package com.nju.book.controller;

import com.nju.book.model.MainType;
import com.nju.book.model.SubType;
import com.nju.book.service.TypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @RequestMapping("selMainType")
    public List<MainType> selMainType(){
        return typeService.selMainType();
    }

    @RequestMapping("selSubType")
    public List<SubType> selSubType(){
        return typeService.selSubType();
    }
}
