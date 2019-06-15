package com.marshall.sky.user.service;

import com.marshall.sky.user.mapper.UserMapper;
import com.marshall.sky.user.model.UserInfo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public boolean create(UserInfo userInfo) {
    return userMapper.create(userInfo);
  }

  @Override
  public List<UserInfo> list(int page, int count) {
    return userMapper.list(page, count);
  }

  @Override
  public Optional<UserInfo> getById(long userId) {
    return Optional.ofNullable(userMapper.getById(userId));
  }
}
