package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogEsService {

    long count();

    Blog save(Blog blog);

    void delete(Blog blog);

    Iterable<Blog> getAll();

    List<Blog> getByTitle(String blogTitle);

    Page<Blog> pageQuery(Integer pageNo, Integer pageSize, String kw);

}