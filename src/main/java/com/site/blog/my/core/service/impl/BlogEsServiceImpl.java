package com.site.blog.my.core.service.impl;

import com.site.blog.my.core.dao.BlogEsRepository;
import com.site.blog.my.core.entity.Blog;
import com.site.blog.my.core.service.BlogEsService;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogEsServiceImpl implements BlogEsService {

    @Autowired
    private BlogEsRepository blogEsRepository;


    @Override
    public long count() {
        return blogEsRepository.count();
    }

    @Override
    public Blog save(Blog blog) {
        return blogEsRepository.save(blog);
    }

    @Override
    public void delete(Blog blog) {
        blogEsRepository.delete(blog);
//        blogEsRepository.deleteById(blog.getSkuId());
    }

    @Override
    public Iterable<Blog> getAll() {
        return blogEsRepository.findAll();
    }

    @Override
    public List<Blog> getByTitle(String blogTitle) {
        List<Blog> list = new ArrayList<>();
        final MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(blogTitle, "blogTitle", "blogContent");
//        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("blogTitle", blogTitle);
        Iterable<Blog> iterable = blogEsRepository.search(multiMatchQueryBuilder);

        iterable.forEach(e->list.add(e));
        return list;
    }

    @Override
    public Page<Blog> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchPhraseQuery("blogTitle", kw))
                .withQuery(QueryBuilders.multiMatchQuery(kw, "blogTitle", "blogContent"))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return blogEsRepository.search(searchQuery);
    }
}