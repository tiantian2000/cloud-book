package com.nju.book.shop.controller;


import com.nju.book.shop.model.Cart;
import com.nju.book.shop.service.CartService;
import com.nju.book.shop.vo.ResultVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping("add")
    public ResultVO add(@RequestBody Cart cart){
        cartService.add(cart);
        ResultVO resultVO = new ResultVO(200);
        return resultVO;
    }

    @RequestMapping("selByUser/{userId}")
    public List<Cart> selByUser(@PathVariable Integer userId){
        return cartService.selByUser(userId);
    }

    @RequestMapping("del/{id}")
    public ResultVO del(@PathVariable Integer id){
        cartService.del(id);
        ResultVO resultVO = new ResultVO(200);
        return resultVO;
    }
}
