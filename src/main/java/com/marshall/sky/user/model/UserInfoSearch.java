package com.marshall.sky.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marshall.sky.core.elasticsearch.model.Search;
import com.marshall.sky.core.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document(indexName = "user_database", type = "user_info")
public class UserInfoSearch extends Search {

  @Id
  @JsonProperty("user_id")
  Long userId;
  @JsonProperty("nick_name")
  String nickName;
  @JsonProperty("status")
  StatusEnum status;


  public static UserInfoSearch transform(UserInfo userInfo) {
    return UserInfoSearch.builder()
        .nickName(userInfo.getNickName())
        .status(userInfo.getStatus())
        .userId(userInfo.getUserId())
        .build();
  }

}
