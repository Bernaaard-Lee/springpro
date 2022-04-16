package com.bookshop.member.controller;

import com.bookshop.common.base.BaseController;
import com.bookshop.member.service.MemberService;
import com.bookshop.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController{
    @Autowired
    MemberService memberService;

    @Autowired
    MemberVO memberVO;

    @Override
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam Map<String, String> loginMap,
                              HttpServletResponse response, HttpServletRequest request) throws Exception{
        ModelAndView mav = new ModelAndView();
        memberVO = memberService.login(loginMap);
        if (memberVO != null && memberVO.getMember_id()!= null){    // 로그인 성공 시
            HttpSession session = request.getSession();             // 서블렛 지정
            session = request.getSession();                         // 세션 받아옴
            session.setAttribute("isLogOn", true);      // isLogOn , 세션 활성화
            session.setAttribute("memberInfo", memberVO);     // 회원정보

            String action = (String) session.getAttribute("action");      // 세션 타입 변환
            if (action != null && action.equals("/order/orderEachGoods.do")){   // 세션이 존재하고 order..와 같으면
                mav.setViewName("forward:"+action);                             // action으로 이동
            }else {
                mav.setViewName("redirect:/main/main.do");                      // 아니라면 메인페이지로 이동
            }
        } else {
            String message = "아이디 혹은 비밀번호가 틀립니다. 다시 시도해주세요";      // 로그인 실패 시
            mav.addObject("message", message);                      // 메시지 출력
            mav.setViewName("/member/loginForm");                               // 로그인창 매핑
        }
        return mav;
    }

    @Override
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView();
        HttpSession session = request.getSession();
        session.setAttribute("isLogOn", false);     // Login 세선 비활성화
        session.removeAttribute("memberInfo");           // 회원정보 세션 삭제
        mav.setViewName("redirect:/main/main.do");            // 메인페이지로 이동
        return mav;
    }

    @Override
    @RequestMapping(value = "/addMember.do", method = RequestMethod.POST)
    public ResponseEntity addMember(@ModelAttribute("memberVO") MemberVO _memberVO,
                                    HttpServletResponse response, HttpServletRequest request) throws Exception{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String message = null;
        ResponseEntity resEntity = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
        try {
            memberService.addMember(_memberVO);
            message = "<script>";
            message += "alert('회원가입이 완료됐습니다.');";
            message += "location.href='"+request.getContextPath()+"/member/loginForm.do';";
            message += "</script>";
        } catch (Exception e) {
            message = "<script>";
            message += "alert('오류가 발생했습니다. 다시 시도해주세요');";
            message += "location.href='"+request.getContextPath()+"/member/memberForm.do';";
            message += "</script>";
            e.printStackTrace();
        }
        resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
        return resEntity;
    }
    @Override
    @RequestMapping(value = "/overlapped.do", method = RequestMethod.POST)
    public ResponseEntity overlapped(@RequestParam("id") String id, HttpServletResponse response, HttpServletRequest request) throws Exception{
        ResponseEntity resEntity = null;
        String result = memberService.overlapped(id);
        resEntity = new ResponseEntity(result, HttpStatus.OK);
        return resEntity;
    }
}
