package com.nju.book.shop.mapper;



import com.nju.book.shop.model.Orders;
import com.nju.book.shop.vo.OrdersSumVO;

import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Orders record);

    Orders selectByPrimaryKey(Integer id);

    List<Orders> selectAll();

    int updateByPrimaryKey(Orders record);

    //根据用户ID查询所有订单
    List<Orders> selectConnAll(Integer userId);

    List<Orders> queryAll(Integer userId);

    //根据用户和年统计每个月的销费金额
    List<OrdersSumVO> sumByMonth(OrdersSumVO ordersSumVO);
}