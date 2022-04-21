package com.bookshop.mypage.service;

import com.bookshop.member.vo.MemberVO;
import com.bookshop.order.vo.OrderVO;

import java.util.List;
import java.util.Map;

public interface MyPageService {
    public List<OrderVO> listMyOrderGoods(String member_id) throws Exception;

    public void cancelOrder(String order_id) throws Exception;

    public MemberVO modifyMyInfo(Map memberMap) throws Exception;

    public List<OrderVO> listMyOrderHistory(Map<String, String> dateMap) throws Exception;
}
