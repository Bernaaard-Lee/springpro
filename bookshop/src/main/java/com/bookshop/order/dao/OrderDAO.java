package com.bookshop.order.dao;

import com.bookshop.order.vo.OrderVO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OrderDAO {
    public List<OrderVO> listMyOrderGoods(OrderVO orderVO) throws DataAccessException;

    public void insertNewOrder(List<OrderVO> myOrderList) throws DataAccessException;

    public void removeGoodsFromCart(List<OrderVO> myOrderList) throws DataAccessException;

    public OrderVO findMyOrder(String order_id) throws DataAccessException;
}
