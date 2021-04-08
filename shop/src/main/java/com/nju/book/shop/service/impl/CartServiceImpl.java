package com.nju.book.shop.service.impl;


import client.BookClient;
import com.nju.book.shop.mapper.CartMapper;
import com.nju.book.shop.model.Cart;
import com.nju.book.shop.service.CartService;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BookClient bookClient;

    @Override
    public void add(Cart cart) {
        //查询当前的用户和选购图书的购物车记录
        Cart qCart = cartMapper.selectByUserBook(cart);
        if(qCart == null){ //购物车中没有此图书,则新增
            cartMapper.insert(cart);
        }else{ //有此图书,更新数量
            qCart.setNum(qCart.getNum()+cart.getNum());
            cartMapper.updateByPrimaryKey(qCart);
        }

    }

    @Override
    public List<Cart> selByUser(Integer userId) {
        List<Cart> list = cartMapper.selectByUser(userId);
        //遍历购物车list,每个购物车项取图书信息
        for(Cart cart: list){
            //调用book微服务,根据ID取图书
            Book book = bookClient.selById(cart.getBookId());
            cart.setBook(book);
        }
        return list;
    }

    @Override
    public void del(Integer id) {
        cartMapper.deleteByPrimaryKey(id);
    }
}
