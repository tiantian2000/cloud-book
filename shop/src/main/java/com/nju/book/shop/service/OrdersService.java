package com.nju.book.shop.service;

import com.github.pagehelper.PageInfo;
import com.nju.book.shop.model.OrderItems;
import com.nju.book.shop.model.Orders;
import com.nju.book.shop.vo.OrdersSumVO;


import java.util.List;

public interface OrdersService {

    String add(Orders orders);

    PageInfo<Orders> selConnAll(Integer userId, Integer currPage);

    PageInfo<Orders> selAll(Integer userId, Integer currPage);

    /**
     * 根据订单id查询订单明细
     */
    List<OrderItems> selAllOrderItems(Integer orderId);

    List<OrdersSumVO> sumByMonth(OrdersSumVO ordersSumVO);
}
