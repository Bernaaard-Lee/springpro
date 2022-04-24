package com.bookshop.admin.goods.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AdminGoodsController {
    @RequestMapping(value = "/addNewGoodsImage.do", method = RequestMethod.POST)
    public void addNewGoodsImage(MultipartHttpServletRequest multipartHttpServletRequest, HttpServletResponse response) throws Exception;

    @RequestMapping(value = "removeGoodsImage.do", method = RequestMethod.POST)
    public void removeGoodsImage(@RequestParam("goods_id") int goods_id,
                          @RequestParam("image_id") int image_id,
                          @RequestParam("imageFileName") String imageFileName,
                          HttpServletResponse response, HttpServletRequest request) throws Exception;
}
