package com.marshall.sky.user.UserHelper;

import com.marshall.sky.core.enums.GenderEnum;
import com.marshall.sky.core.enums.StatusEnum;
import com.marshall.sky.user.model.UserInfo;
import java.util.Optional;

public class UserHelper {

  public static Optional<UserInfo> mockUserInfo() {
    return Optional.ofNullable(UserInfo.builder()
        .userId(System.currentTimeMillis())
        .nickName("abay")
        .status(StatusEnum.ONLINE)
        .gender(GenderEnum.MAN)
        .build());
  }
}
