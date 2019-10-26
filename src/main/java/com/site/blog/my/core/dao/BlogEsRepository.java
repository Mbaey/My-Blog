package com.site.blog.my.core.dao;

import com.site.blog.my.core.entity.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogEsRepository extends ElasticsearchRepository<Blog, Long> {

}