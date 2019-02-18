package com.cordyceps.service;

import com.cordyceps.common.ServerResponse;

public interface ITransportService {

    ServerResponse addTransport(Integer productId, String location, String longitude, String latitude,  Integer status);

    ServerResponse updateTransport(Integer transportId, String location, String longitude, String latitude);

    ServerResponse getTransportListByProductId(Integer productId, int pageNum, int pageSize);
}
