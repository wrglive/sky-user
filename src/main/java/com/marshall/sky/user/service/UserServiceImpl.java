package com.marshall.sky.user.service;

import com.google.common.collect.Lists;
import com.marshall.sky.user.mapper.UserMapper;
import com.marshall.sky.user.model.UserInfo;
import com.marshall.sky.user.model.UserInfoSearch;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRedisManage userRedisManage;
  @Autowired
  UserMapper userMapper;
  @Autowired
  SearchService searchService;

  @Override
  public boolean create(UserInfo userInfo) {
    userInfo.setCreateAt(System.currentTimeMillis());
    boolean rst = userMapper.create(userInfo);
    if (rst) {
      userRedisManage.create(userInfo);
      searchService.save(UserInfoSearch.transform(userInfo));
    }
    return rst;
  }

  @Override
  public List<UserInfo> list(int page, int count) {
    return userMapper.list(count, page * count);
  }

  @Override
  public List<Long> search(String keyword, int page, int count) {
    NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
    NativeSearchQuery nativeSearchQuery = builder.withFields("user_id", "nick_name")
        .withSearchType(SearchType.DFS_QUERY_THEN_FETCH)
        .withQuery(QueryBuilders.queryStringQuery(keyword))
        .build();
    Iterable<UserInfoSearch> userInfoSearchIterable = searchService.search(nativeSearchQuery);
    return Lists.newArrayList(userInfoSearchIterable)
        .stream()
        .map(UserInfoSearch::getUserId)
        .collect(
            Collectors.toList());
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
