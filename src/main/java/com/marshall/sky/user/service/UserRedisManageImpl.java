package com.marshall.sky.user.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.marshall.sky.user.model.UserInfo;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserRedisManageImpl implements UserRedisManage {

  private static final String BUILD_USER_COUNT_KEY = "sky.user.build.count";
  @Autowired
  StringRedisTemplate redisTemplate;

  private ValueOperations<String, String> redisString() {
    return redisTemplate.opsForValue();
  }

  private String getUserPojoKey(long userId) {
    return "sky.user.pojo.id:" + userId;
  }


  @Override
  public boolean create(UserInfo userInfo) {
    String redisKey = getUserPojoKey(userInfo.getUserId());
    redisString().set(redisKey, JSONObject.toJSONString(userInfo));
    return true;
  }

  @Override
  public List<UserInfo> multiGet(Collection<Long> ids) {
    if (CollectionUtils.isEmpty(ids)) {
      return Lists.newArrayList();
    }

    return redisString().multiGet(ids.stream()
        .filter(Objects::nonNull)
        .map(this::getUserPojoKey)
        .collect(Collectors.toList()))
        .stream()
        .filter(StringUtils::isNotBlank)
        .map(json -> JSONObject.parseObject(json, UserInfo.class))
        .collect(Collectors.toList());
  }

  @Override
  public long buildUserId() {
    Long buildCount = redisString().increment(BUILD_USER_COUNT_KEY);
    redisTemplate.expire(BUILD_USER_COUNT_KEY, 1000, TimeUnit.MILLISECONDS);
    return System.currentTimeMillis() * 1000 + buildCount;
  }
}
