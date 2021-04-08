package com.nju.book.shop.mapper;



import com.nju.book.shop.model.Cart;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    Cart selectByPrimaryKey(Integer id);

    List<Cart> selectAll();

    int updateByPrimaryKey(Cart record);

    //根据用户ID查询购物车信息
    List<Cart> selectByUser(Integer userId);

    //查询指定用户和图书的记录
    Cart selectByUserBook(Cart cart);
}