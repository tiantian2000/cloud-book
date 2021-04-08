package com.nju.book.shop.mapper;


import com.nju.book.shop.model.OrderItems;

import java.util.List;

public interface OrderItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderItems record);

    OrderItems selectByPrimaryKey(Integer id);

    List<OrderItems> selectAll();

    int updateByPrimaryKey(OrderItems record);

    /**
     * 根据订单id查询订单明细
     */
    List<OrderItems> selectByOrder(Integer orderId);
}