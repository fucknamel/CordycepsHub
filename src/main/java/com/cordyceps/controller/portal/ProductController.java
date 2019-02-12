package com.cordyceps.controller.portal;

import com.cordyceps.common.ServerResponse;
import com.cordyceps.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "get_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDetail(Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordAndCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }
}
