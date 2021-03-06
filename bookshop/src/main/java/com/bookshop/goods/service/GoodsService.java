package com.bookshop.goods.service;

import com.bookshop.goods.vo.GoodsVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    public Map<String, List<GoodsVO>> listGoods() throws Exception;

    public Map goodsDetail(String goods_id) throws Exception;

    public List<GoodsVO> searchGoods(String searchWord) throws Exception;

    public List<String> keywordSearch(String keyword) throws Exception;
}
