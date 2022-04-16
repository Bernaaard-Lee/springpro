package com.bookshop.goods.controller;

import com.bookshop.common.base.BaseController;
import com.bookshop.goods.service.GoodsService;
import com.bookshop.goods.vo.GoodsVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("goodsController")  // controller 선언
@RequestMapping(value = "/goods")
public class GoodsControllerImpl extends BaseController implements GoodsController{
    @Autowired
    GoodsService goodsService;

    // localhost:8090/goods/goodsDetail.do?goods_id={goods_id}
    @RequestMapping(value="/goodsDetail.do" ,method = RequestMethod.GET)
    public ModelAndView goodsDetail(@RequestParam("goods_id") String goods_id,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName=(String)request.getAttribute("viewName");   //
        HttpSession session=request.getSession();           // session을 받아옴
        Map goodsMap=goodsService.goodsDetail(goods_id);    // goodsService의 goodsDetail함수(goods_id값)을 goodsMap에 mapping
        ModelAndView mav = new ModelAndView(viewName);      // mav 호출
        mav.addObject("goodsMap", goodsMap);    // mav에 goodsMap에 저장된 값 반영
        GoodsVO goodsVO=(GoodsVO)goodsMap.get("goodsVO");   // ?
        addGoodsInQuick(goods_id,goodsVO,session);          // 최근본상품함수 (goods_id, 상품정보, 세션)
        return mav;
    }

    @RequestMapping(value="/keywordSearch.do",method = RequestMethod.GET,produces = "application/text; charset=utf8")
    public @ResponseBody String  keywordSearch(@RequestParam("keyword") String keyword,
                          HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //System.out.println(keyword);
        if(keyword == null || keyword.equals(""))
            return null ;

        keyword = keyword.toUpperCase();
        List<String> keywordList =goodsService.keywordSearch(keyword);

        // 최종 완성될 JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", keywordList);

        String jsonInfo = jsonObject.toString();
        // System.out.println(jsonInfo);
        return jsonInfo ;
    }
    @RequestMapping(value="/searchGoods.do" ,method = RequestMethod.GET)
    public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        String viewName=(String)request.getAttribute("viewName");
        List<GoodsVO> goodsList=goodsService.searchGoods(searchWord);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsList", goodsList);
        return mav;

    }

    private void addGoodsInQuick(String goods_id,GoodsVO goodsVO,HttpSession session){
        boolean already_existed=false;
        List<GoodsVO> quickGoodsList; //최근 본 상품 저장 ArrayList
        quickGoodsList=(ArrayList<GoodsVO>)session.getAttribute("quickGoodsList");

        if(quickGoodsList!=null){
            if(quickGoodsList.size() < 4){ //미리본 상품 리스트에 상품개수가 세개 이하인 경우
                for(int i=0; i<quickGoodsList.size();i++){
                    GoodsVO _goodsBean=(GoodsVO)quickGoodsList.get(i);
                    if(goods_id.equals(_goodsBean.getGoods_id())){
                        already_existed=true;
                        break;
                    }
                }
                if(already_existed==false){         // 최근 본 목록에 없다면
                    quickGoodsList.add(goodsVO);    // qucickGoodsList에 상품정보 추가
                }
            }

        }else{
            quickGoodsList =new ArrayList<GoodsVO>();
            quickGoodsList.add(goodsVO);

        }
        session.setAttribute("quickGoodsList",quickGoodsList);
        session.setAttribute("quickGoodsListNum", quickGoodsList.size());
    }
}
