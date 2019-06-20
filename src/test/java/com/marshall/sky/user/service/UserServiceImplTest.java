package com.marshall.sky.user.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.marshall.sky.core.test.BaseServiceTest;
import com.marshall.sky.user.UserHelper.UserHelper;
import com.marshall.sky.user.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("用户的测试")
public class UserServiceImplTest extends BaseServiceTest {

  @InjectMocks
  UserServiceImpl userService;

  @Mock
  UserRedisManage userRedisManage;
  @Mock
  UserMapper userMapper;


  @Test
  public void create() {
    when(userMapper.create(any())).thenReturn(true);
    when(userRedisManage.create(any())).thenReturn(true);
    boolean rst = userService.create(UserHelper.mockUserInfo().get());
    Assert.assertTrue("创建失败了兄弟", rst);
  }
}