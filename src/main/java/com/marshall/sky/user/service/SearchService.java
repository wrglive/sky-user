package com.marshall.sky.user.service;

import com.marshall.sky.user.model.UserInfoSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

@Service
public interface SearchService extends ElasticsearchRepository<UserInfoSearch, Long> {

}
