package com.marshall.sky.user.mapper;

import com.marshall.sky.core.enums.GenderEnum;
import com.marshall.sky.core.enums.StatusEnum;
import com.marshall.sky.user.model.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

  @Insert("insert into user_info"
      + "(user_id, "
      + "nick_name, "
      + "gender, "
      + "avatar_id, "
      + "status) "
      + "values "
      + "(#{userId},"
      + "#{nickName}, "
      //下面type 写的是class 这里也写得class 找bug找了半天，fuck
      + "#{gender,typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler}, "
      + "#{avatarId}, "
      + "#{status,typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler})")
  boolean create(UserInfo userInfo);

  @Select("select * from user_info limit #{offset}, #{limit}")
  @Results({
      @Result(property = "gender", column = "gender", typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler.class, javaType = GenderEnum.class),
      @Result(property = "status", column = "status", typeHandler = com.marshall.sky.core.mybatis.EnumTransformHandler.class, javaType = StatusEnum.class)
  })
  List<UserInfo> list(int limit, int offset);

  @Select("select * from user_info where user_id = #{userId}")
  UserInfo getById(long userId);
}
