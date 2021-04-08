package com.nju.book.shop.controller;


import com.nju.book.shop.model.OrderItems;
import com.nju.book.shop.service.OrdersService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Resource
    private OrdersService ordersService;

    @RequestMapping("list/{orderId}")
    public List<OrderItems> list(@PathVariable Integer orderId){
        return ordersService.selAllOrderItems(orderId);
    }
}
