package com.marshall.sky.user.service;

import com.marshall.sky.user.model.UserInfo;
import java.util.List;
import java.util.Optional;

public interface UserService {

  boolean create(UserInfo userInfo);

  List<UserInfo> list(int page, int count);

  Optional<UserInfo> getById(long userId);

}
