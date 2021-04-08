package com.nju.book.shop.service;



import com.nju.book.shop.model.Cart;

import java.util.List;

public interface CartService {

    void add(Cart cart);

    List<Cart> selByUser(Integer userId);

    void del(Integer id);
}
