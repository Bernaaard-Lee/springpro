package com.bookshop.main;

import com.bookshop.common.base.BaseController;
import com.bookshop.goods.service.GoodsService;
import com.bookshop.goods.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("mainController")
@EnableAspectJAutoProxy
public class MainController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    // localhost:8090/main/main.do
    @RequestMapping(value = "/main/main.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session;        // 세션 생성
        ModelAndView mav = new ModelAndView();      // mav 선언
        String viewName = (String) request.getAttribute("viewName");    //
        mav.setViewName(viewName);

        session = request.getSession();     // 세션 받아옴
        session.setAttribute("side_menu", "user");      // user값 받아 side_menu 설정
        Map<String, List<GoodsVO>> goodsMap = goodsService.listGoods();     // 상품목록 goodsMap에 저장
        mav.addObject("goodsMap", goodsMap);                // mav에 오브젝트 추가
        return mav;         // mav 반환
    }
}
