package com.cordyceps.controller.portal;

import com.cordyceps.common.Const;
import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.User;
import com.cordyceps.service.ITransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/transport/")
public class TransportController {

    @Autowired
    private ITransportService iTransportService;

    @RequestMapping(value = "get_transport_list.do")
    @ResponseBody
    public ServerResponse getTransportList(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam("productId") Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return iTransportService.getTransportListByProductId(productId, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("用户未登陆，请登陆");
    }

    @RequestMapping(value = "get_qrcode.do")
    @ResponseBody
    public ServerResponse getQRcode(HttpSession session, Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        return iTransportService.getQRcodeById(productId);
    }
}
