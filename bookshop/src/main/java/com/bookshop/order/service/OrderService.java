package com.bookshop.order.service;

import com.bookshop.order.vo.OrderVO;

import java.util.List;

public interface OrderService {
    public void addNewOrder(List<OrderVO> myOrderList) throws Exception;
}
