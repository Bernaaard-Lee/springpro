package com.bookshop.goods.dao;

import com.bookshop.goods.vo.GoodsVO;
import com.bookshop.goods.vo.ImageFileVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("goodsDAO")
public class GoodsDAOImpl implements GoodsDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException {
        // mapper/goods.xml 의 selectGoodsList 쿼리문 동작
        List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
        return goodsList;
    }

    @Override
    public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException{
        // mapper/goods.xml 의 selectGoodsDetail 쿼리문 작동
        GoodsVO goodsVO=(GoodsVO) sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
        return goodsVO;
    }

    @Override
    public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException {
        // mapper/goods.xml 의 selectGoodsDetailImage 쿼리문 작동
        List<ImageFileVO> imageList = (ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage", goods_id);
        return imageList;
    }
}
