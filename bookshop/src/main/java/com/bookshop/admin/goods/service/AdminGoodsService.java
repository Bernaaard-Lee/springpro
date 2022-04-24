package com.bookshop.admin.goods.service;

import com.bookshop.goods.vo.GoodsVO;
import com.bookshop.goods.vo.ImageFileVO;

import java.util.List;
import java.util.Map;

public interface AdminGoodsService {
    public List<GoodsVO> listNewGoods(Map<String, Object> condMap) throws Exception;

    public int addNewGoods(Map newGoodsMap) throws Exception;

    public void modifyGoodsInfo(Map<String, String> goodsMap) throws Exception;

    public Map goodsDetail(int goods_id) throws Exception;

    public void addNewGoodsImage(List<ImageFileVO> imageFileList) throws Exception;

    public void modifyGoodsImage(List<ImageFileVO> imageFileList) throws Exception;

    public void removeGoodsImage(int image_id) throws Exception;
}
