package com.bookshop.cart.service;

import com.bookshop.cart.dao.CartDAO;
import com.bookshop.cart.vo.CartVO;
import com.bookshop.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cartService")
@Transactional(propagation = Propagation.REQUIRED)
public class CartServiceImpl implements CartService{

    @Autowired
    CartDAO cartDAO;

    public Map<String, List> myCartList(CartVO cartVO) throws Exception{
        Map<String, List> cartMap = new HashMap<String, List>();
        List<CartVO> myCartList = cartDAO.selectCartList(cartVO);
        if (myCartList.size()==0) {
            return null;
        }
        List<GoodsVO> myGoodsList = cartDAO.selectGoodsList(myCartList);
        cartMap.put("myCartList", myCartList);
        cartMap.put("myGoodsList", myGoodsList);
        return cartMap;
    }
    public boolean findCartGoods(CartVO cartVO) throws Exception{
        return cartDAO.selectCountInCart(cartVO);
    }

    public void addGoodsInCart(CartVO cartVO) throws Exception{
        cartDAO.insertGoodsInCart(cartVO);
    }

    public boolean modifyCartQty(CartVO cartVO) throws Exception{
        boolean result = true;
        cartDAO.updateCartGoodsQty(cartVO);
        return result;
    }

    public void removeCartGoods(int cart_id) throws Exception{
        cartDAO.deleteCartGoods(cart_id);
    }

}
