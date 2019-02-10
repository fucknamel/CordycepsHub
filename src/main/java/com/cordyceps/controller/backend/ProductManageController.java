package com.cordyceps.controller.backend;

import com.cordyceps.common.Const;
import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.Product;
import com.cordyceps.pojo.User;
import com.cordyceps.service.IProductService;
import com.cordyceps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/product/")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "save.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.saveOrUpdateProduct(product);
        } else {
            return ServerResponse.createByErrorMessage("无操作权限");
        }
    }

    @RequestMapping(value = "set_sale_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setSaleStatus(HttpSession session, Integer productId, Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.setSaleStatus(productId, status);
        }
        return ServerResponse.createByErrorMessage("无操作权限");
    }

    @RequestMapping(value = "get_detail.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getDetail(HttpSession session, Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆管理员");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            return iProductService.manageProductDetail(productId);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "get_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，需要登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.getProductList(pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("无权限操作");
    }

    @RequestMapping(value = "search.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse productSearch(HttpSession session, String productTitle, Integer productId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，需要登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iProductService.searchProduct(productTitle, productId, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("无操作权限");
    }
}
