package com.bookshop.member.controller;

import com.bookshop.member.vo.MemberVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface MemberController {
    public ModelAndView login(@RequestParam Map<String, String> loginMap,
                       HttpServletResponse response, HttpServletRequest request) throws Exception;


    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
                             HttpServletResponse response, HttpServletRequest request) throws Exception;

    public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception;
}
