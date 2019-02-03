package com.cordyceps.service;

import com.cordyceps.common.ServerResponse;
import com.cordyceps.pojo.User;

public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> selectQuestion(String username);
}
