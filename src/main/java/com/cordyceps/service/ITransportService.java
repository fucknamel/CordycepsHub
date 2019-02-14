package com.cordyceps.service;

import com.cordyceps.common.ServerResponse;

public interface ITransportService {

    ServerResponse addTransport(Integer productId, String location, String longitude, String latitude,  Integer status);
}
