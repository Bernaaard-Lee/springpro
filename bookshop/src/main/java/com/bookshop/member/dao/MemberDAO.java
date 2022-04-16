package com.bookshop.member.dao;

import com.bookshop.member.vo.MemberVO;
import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface MemberDAO {
    public MemberVO login(Map loginMap) throws DataAccessException;

    public void insertNewMember(MemberVO memberVO) throws DataAccessException;

    public String selectOverlappedID(String id) throws DataAccessException;
}
