package com.cordyceps.service.impl;

import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.dao.TransportMapper;
import com.cordyceps.pojo.Transport;
import com.cordyceps.service.ITransportService;
import com.cordyceps.util.PropertiesUtil;
import com.cordyceps.util.QRCodeUtil;
import com.cordyceps.vo.TransportListVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("iTransportService")
public class TransportServiceImpl implements ITransportService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private TransportMapper transportMapper;

    public ServerResponse addTransport(Integer productId, String location, String longitude, String latitude, Integer status) {
        if (productId == null || StringUtils.isBlank(location) ||
                StringUtils.isBlank(latitude) || StringUtils.isBlank(longitude)) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Transport transport = new Transport();
        transport.setProductId(productId);
        transport.setLocation(location);
        transport.setStatus(status);
        try {
            BigDecimal longi = new BigDecimal(longitude);
            BigDecimal lati = new BigDecimal(latitude);
            transport.setLongitude(longi);
            transport.setLatitude(lati);

            int rowResult = transportMapper.insert(transport);
            if (rowResult > 0){
                return ServerResponse.createBySuccessMessage("新增运输信息成功");
            }
        }catch (NumberFormatException e){
            logger.error("经纬度格式错误", e);
        }
        return ServerResponse.createByErrorMessage("新增运输信息失败");
    }

    public ServerResponse updateTransport(Integer transportId, String location, String longitude, String latitude){
        if (transportId == null || location == null || longitude == null || latitude == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Transport transport = new Transport();
        transport.setId(transportId);
        transport.setLocation(location);
        try{
            BigDecimal longi = new BigDecimal(longitude);
            BigDecimal lati = new BigDecimal(latitude);
            transport.setLongitude(longi);
            transport.setLatitude(lati);

            int rowResult = transportMapper.updateByPrimaryKeySelective(transport);
            if (rowResult > 0) {
                return ServerResponse.createBySuccessMessage("更新地点成功");
            }
        }catch (NumberFormatException e){
            logger.error("经纬度格式错误", e);
        }
        return ServerResponse.createByErrorMessage("更新地点失败");
    }

    public ServerResponse getTransportListByProductId(Integer productId, int pageNum, int pageSize){
        if (productId == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Transport> transportList = transportMapper.selectListByProductId(productId);

        List<TransportListVo> transportListVoList = Lists.newArrayList();
        for (Transport transportItem : transportList){
            TransportListVo transportListVo = assembleTransportListVo(transportItem);
            transportListVoList.add(transportListVo);
        }
        PageInfo pageInfo = new PageInfo(transportList);
        pageInfo.setList(transportListVoList);

        return ServerResponse.createBySuccess(pageInfo);
    }

    private TransportListVo assembleTransportListVo(Transport transport){
        TransportListVo transportListVo = new TransportListVo();
        transportListVo.setTransportId(transport.getId());
        transportListVo.setProductId(transport.getProductId());
        transportListVo.setLocation(transport.getLocation());
        transportListVo.setLongitude(transport.getLongitude());
        transportListVo.setLatitude(transport.getLatitude());
        transportListVo.setStatus(transport.getStatus());

        return transportListVo;
    }

    public ServerResponse getList(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Transport> list = transportMapper.selectList();
        if (list != null) {
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.createBySuccess(pageInfo);
        }
        return ServerResponse.createByErrorMessage("没有记录");
    }

    public ServerResponse getQRcodeById(Integer productId){
        if (productId != null) {
            String ans = PropertiesUtil.getProperty("http.prefix") + "transport/get_transport_list.do?productId=" + productId;
            String path = QRCodeUtil.getQRcodePath(ans);

            return ServerResponse.createBySuccess(path);
        }
        return ServerResponse.createByErrorMessage("参数错误或获取二维码失败");
    }
}
