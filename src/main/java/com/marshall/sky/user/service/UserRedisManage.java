package com.marshall.sky.user.service;

import com.marshall.sky.user.model.UserInfo;
import java.util.Collection;
import java.util.List;

public interface UserRedisManage {

  boolean create(UserInfo userInfo);

  List<UserInfo> multiGet(Collection<Long> ids);

  long buildUserId();

}
