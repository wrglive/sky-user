package com.marshall.sky.user.service;

import com.google.common.collect.Lists;
import com.marshall.sky.user.mapper.UserMapper;
import com.marshall.sky.user.model.UserInfo;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRedisManage userRedisManage;
  @Autowired
  UserMapper userMapper;

  @Override
  public boolean create(UserInfo userInfo) {
    userInfo.setCreateAt(System.currentTimeMillis());
    boolean rst = userMapper.create(userInfo);
    if (rst) {
      userRedisManage.create(userInfo);
    }
    return rst;
  }

  @Override
  public List<UserInfo> list(int page, int count) {
    return userMapper.list(count, page * count);
  }

  @Override
  public List<UserInfo> multiGet(Collection<Long> ids) {
    return userRedisManage.multiGet(ids);
  }

  @Override
  public Optional<UserInfo> getById(long userId) {
    return userRedisManage.multiGet(Lists.newArrayList(userId))
        .stream().findFirst();
  }

  @Override
  public long buildUserId() {
    return userRedisManage.buildUserId();
  }
}
