package com.cordyceps.service.impl;

import com.cordyceps.common.ResponseCode;
import com.cordyceps.common.ServerResponse;
import com.cordyceps.dao.TransportMapper;
import com.cordyceps.pojo.Transport;
import com.cordyceps.service.ITransportService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
}
