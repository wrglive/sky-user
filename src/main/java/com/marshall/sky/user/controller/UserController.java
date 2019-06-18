package com.marshall.sky.user.controller;

import com.marshall.sky.core.enums.GenderEnum;
import com.marshall.sky.core.enums.StatusEnum;
import com.marshall.sky.user.model.UserInfo;
import com.marshall.sky.user.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/create.json")
  public boolean create(@RequestParam(name = "nick_name") String nickName, GenderEnum gender,
      StatusEnum status, @RequestParam(name = "avatar_id") Long avatarId) {
    return userService.create(
        UserInfo
            .builder()
            .nickName(nickName)
            .userId(System.currentTimeMillis())
            .avatarId(avatarId)
            .gender(gender).status(status)
            .build());
  }

  @GetMapping("/list.json")
  public List<UserInfo> list(int page, int count) {
    return userService.list(page, count);
  }

  @GetMapping("/get.json")
  public UserInfo get(long id) {
    return userService.getById(id).orElse(null);
  }

}
