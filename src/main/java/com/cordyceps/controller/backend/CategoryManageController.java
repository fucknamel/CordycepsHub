package com.cordyceps.controller.backend;

import com.cordyceps.common.Const;
import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.User;
import com.cordyceps.service.ICategoryService;
import com.cordyceps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/category/")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "add_category.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,@RequestParam("categoryName") String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        //校验一下是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加我们处理分类的逻辑
            return iCategoryService.addCategory(categoryName, parentId);
        }
        else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }


}