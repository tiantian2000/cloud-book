package com.nju.book.shop.controller;

import com.github.pagehelper.PageInfo;

import com.nju.book.shop.model.Orders;
import com.nju.book.shop.model.UserInfo;
import com.nju.book.shop.service.OrdersService;
import com.nju.book.shop.vo.OrdersSumVO;
import com.nju.book.shop.vo.ResultVO;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    @RequestMapping("/add")
    public ResultVO add(@RequestBody Orders orders){
        String code = ordersService.add(orders);
        ResultVO resultVO = new ResultVO(200);
        resultVO.setObject(code);
        return resultVO;
    }

    @RequestMapping("selAll/{currPage}")
    public PageInfo<Orders> selAll(@RequestBody UserInfo user, @PathVariable Integer currPage){
        return ordersService.selConnAll(user.getId(),currPage);
    }

    @RequestMapping("list/{currPage}")
    public PageInfo<Orders> list(@RequestBody UserInfo user, @PathVariable Integer currPage){
        return ordersService.selAll(user.getId(),currPage);
    }

    /**
     * 根据用户统计指定年份每个月的销费金额
     * @param ordersSumVO
     * @return
     */
    @RequestMapping("sumByMonth")
    public List<OrdersSumVO> sumByMonth(@RequestBody OrdersSumVO ordersSumVO){
        return ordersService.sumByMonth(ordersSumVO);
    }


}
