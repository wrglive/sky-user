package com.marshall.sky.user.mapper;

import com.marshall.sky.user.model.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

  @Insert("insert into user_info"
      + "(user_id, nick_name, gender, avatar_id, status) "
      + "values "
      + "(#{userId}, #{nickName}, #{gender}, #{avatarId}, #{status})")
  boolean create(UserInfo userInfo);

  @Select("select * from user_info limit #{page} * #{count}, #{count}")
  List<UserInfo> list(int page, int count);

  @Select("select * from user_info where user_id = #{userId}")
  UserInfo getById(long userId);
}
