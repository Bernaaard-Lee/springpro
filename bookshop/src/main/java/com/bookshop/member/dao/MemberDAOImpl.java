package com.bookshop.member.dao;

import com.bookshop.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("memberDAO")
public class MemberDAOImpl implements MemberDAO{
    @Autowired
    SqlSession sqlSession;

    @Override
    public MemberVO login(Map loginMap) throws DataAccessException {
        MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
        return member;
    }

    @Override
    public void insertNewMember(MemberVO memberVO) throws DataAccessException{
        sqlSession.insert("mapper.member.insertNewMember",memberVO);
    }

    @Override
    public String selectOverlappedID(String id) throws DataAccessException {
        String result =  sqlSession.selectOne("mapper.member.selectOverlappedID",id);
        return result;
    }
}
