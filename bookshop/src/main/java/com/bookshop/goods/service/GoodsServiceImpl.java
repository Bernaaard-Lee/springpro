package com.bookshop.goods.service;

import com.bookshop.goods.dao.GoodsDAO;
import com.bookshop.goods.vo.GoodsVO;
import com.bookshop.goods.vo.ImageFileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsService")
@Transactional(propagation = Propagation.REQUIRED)
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    GoodsDAO goodsDAO;      // 클래스에서 goodsDAO 활용

    public Map<String,List<GoodsVO>> listGoods() throws Exception {
        Map<String,List<GoodsVO>> goodsMap=new HashMap<String,List<GoodsVO>>();
        List<GoodsVO> goodsList=goodsDAO.selectGoodsList("bestseller");     // goodsDAO의 selectGoodsList 함수의 bestseller 칼럼 goodsList에 저장
        goodsMap.put("bestseller",goodsList);           // goodsMap에 bestseller 목록 반영
        goodsList=goodsDAO.selectGoodsList("newbook");      // goodsList에 newbook 추가
        goodsMap.put("newbook",goodsList);              // goodsMap에 newbook 목록 반영

        goodsList=goodsDAO.selectGoodsList("steadyseller"); // goodsList에 steadyseller 목록 추가
        goodsMap.put("steadyseller",goodsList);         // goodsMap에 steady셀러 목록 반영
        return goodsMap;
    }

    public Map goodsDetail(String _goods_id) throws Exception {     // goods_id 값을 받는 goodsDetail 메소드
        Map goodsMap=new HashMap();
        GoodsVO goodsVO = goodsDAO.selectGoodsDetail(_goods_id);   // goodsDAO내 selectGoodsDetail 함수의 goods_id 값을 goodsVO에 저장
        goodsMap.put("goodsVO", goodsVO);                          // goodsMap에 반영
        List<ImageFileVO> imageList =goodsDAO.selectGoodsDetailImage(_goods_id);    // goodsDAO내 selectGoodsDetailImage 함수값을 받아 image
        goodsMap.put("imageList", imageList);                      // goodsMap에 반영
        return goodsMap;
    }
    public List<String> keywordSearch(String keyword) throws Exception {
        List<String> list=goodsDAO.selectKeywordSearch(keyword);
        return list;
    }

    public List<GoodsVO> searchGoods(String searchWord) throws Exception{
        List goodsList=goodsDAO.selectGoodsBySearchWord(searchWord);
        return goodsList;
    }
}
