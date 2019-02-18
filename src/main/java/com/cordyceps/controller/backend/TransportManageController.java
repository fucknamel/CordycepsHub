package com.cordyceps.controller.backend;

import com.cordyceps.common.Const;
import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.User;
import com.cordyceps.service.ITransportService;
import com.cordyceps.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/transport/")
public class TransportManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ITransportService iTransportService;

    @RequestMapping(value = "add_transport.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addTransport(HttpSession session,
                                       @RequestParam("productId") Integer productId,
                                       @RequestParam("location") String location,
                                       @RequestParam("longitude") String longitude,
                                       @RequestParam("latitude") String latitude,
                                       @RequestParam(value = "status", defaultValue = "2") Integer status){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iTransportService.addTransport(productId, location, longitude, latitude, status);
        }
        return ServerResponse.createByErrorMessage("用户无操作权限");
    }

    @RequestMapping(value = "set_transport_location.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setTransportLocation(HttpSession session,
                                          @RequestParam("transportId") Integer transportId,
                                          @RequestParam("location") String location,
                                          @RequestParam("longitude") String longitude,
                                          @RequestParam("latitude") String latitude){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登陆");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iTransportService.updateTransport(transportId, location, longitude, latitude);
        }
        return ServerResponse.createByErrorMessage("用户无操作权限");
    }

    @RequestMapping(value = "get_transport_list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTransportList(HttpSession session,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,@RequestParam("productId") Integer productId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登陆，请登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            return iTransportService.getTransportListByProductId(productId, pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("无操作权限");
    }
}
