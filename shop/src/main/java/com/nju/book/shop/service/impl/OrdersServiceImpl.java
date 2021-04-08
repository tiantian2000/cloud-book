package com.nju.book.shop.service.impl;

import client.BookClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.netflix.discovery.converters.Auto;
import com.nju.book.shop.mapper.OrderItemsMapper;
import com.nju.book.shop.mapper.OrdersMapper;
import com.nju.book.shop.model.OrderItems;
import com.nju.book.shop.model.Orders;
import com.nju.book.shop.service.OrdersService;
import com.nju.book.shop.util.Define;
import com.nju.book.shop.util.GeneratorCode;
import com.nju.book.shop.vo.OrdersSumVO;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private BookClient bookClient;

    @Override
    public String add(Orders orders) {
        //设置订单编号
        orders.setCode(GeneratorCode.generatorOrderCode());
        //设置订单状态
        orders.setStatus("0");
        //充置下订单时间
        orders.setOrderdate(new Date());
        //添加订单
        ordersMapper.insert(orders);
        //添加订单明细
        for(OrderItems orderItems: orders.getOrderItems()){
            //设置订单编号
            orderItems.setOrderId(orders.getId());
            orderItemsMapper.insert(orderItems);
        }
        return orders.getCode();
    }

    @Override
    public PageInfo<Orders> selConnAll(Integer userId, Integer currPage) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage, Define.ADMIN_BOOK_PAGE_SIZE);
        PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersMapper.selectConnAll(userId));
        List<Orders> list = pageInfo.getList();
        //遍历订单
        for(Orders orders: list){
            List<OrderItems> data = orders.getOrderItems();
            //遍历每个订单下的订单名细
            for(OrderItems item: data){
                //调用微服务根据图书ID取图书信息
                Book book = bookClient.selById(item.getBookId());
                item.setBook(book);
            }
        }
        return pageInfo;
    }


    @Override
    public PageInfo<Orders> selAll(Integer userId, Integer currPage) {
        if(currPage == null) currPage = 1;
        PageHelper.startPage(currPage, Define.ADMIN_BOOK_PAGE_SIZE);
        return new PageInfo<Orders>(ordersMapper.queryAll(userId));
    }

    @Override
    public List<OrderItems> selAllOrderItems(Integer orderId) {
        return orderItemsMapper.selectByOrder(orderId);
    }

    @Override
    public List<OrdersSumVO> sumByMonth(OrdersSumVO ordersSumVO) {
        return ordersMapper.sumByMonth(ordersSumVO);
    }
}
