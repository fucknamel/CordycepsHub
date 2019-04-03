package com.cordyceps.controller.portal;

import com.cordyceps.common.Const;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.User;
import com.cordyceps.service.IFileService;
import com.cordyceps.service.IProductService;
import com.cordyceps.service.IUserService;
import com.cordyceps.util.PropertiesUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IFileService iFileService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "get_detail.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getDetail(Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "get_list.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                  @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                  @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductByKeywordAndCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }

    @RequestMapping(value = "get_digger_product.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getListByDigger(HttpSession session,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null){
            if (user.getRole() != Const.Role.ROLE_DIGGER){
                return ServerResponse.createByErrorMessage("用户不是挖掘者，无法查看");
            }
            return iProductService.getProductListByDiggerId(user.getId(), pageNum, pageSize);
        }
        return ServerResponse.createByErrorMessage("用户未登陆，请登录");
    }

    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String targetFileName = iFileService.upload(file, path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri", targetFileName);
        fileMap.put("url", url);

        return ServerResponse.createBySuccess(fileMap);
    }
}
