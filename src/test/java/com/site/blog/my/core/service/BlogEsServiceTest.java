package com.site.blog.my.core.service;

import com.site.blog.my.core.entity.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogEsServiceTest {
    @Autowired
    private BlogEsService blogEsService;
    @Autowired
    private BlogService blogService;

    @Test
    public void count() {
        final int totalBlogs = blogService.getTotalBlogs();
        System.out.println("mysql " + totalBlogs);
        final long count = blogEsService.count();
        System.out.println("es " + count);
    }

    //elastic search 索引建立。，
    // 部署新环境时需要运行该代码
    @Test
    public void save() {
        int count = blogService.getTotalBlogs();
        for (int i = 1; i <= count; i++) {
            final Blog blogById = blogService.getBlogById((long) i);
            System.out.println(blogById);
            blogEsService.save(blogById);
        }
    }

//    @Test
//    public void delete() {
//        for (int i = 1; i < 10; i++) {
//            final Blog blogById = blogService.getBlogById((long) i);
//            System.out.println(blogById);
//
//            blogEsService.delete(blogById);
//        }
//    }

    @Test
    public void getAll() {
        int count = 573;
        for (int i = 1; i < 10; i++) {
            final Blog blogById = blogService.getBlogById((long) i);
//            System.out.println(blogById);
        }
        final long count1 = blogEsService.count();
        System.out.println(count);
    }

    @Test
    public void getByTitle() {
        final List<Blog> spring_idea = blogEsService.getByTitle("mvc");
        System.out.println(spring_idea.size());
        spring_idea.forEach(System.out::println);
    }

    @Test
    public void pageQuery() {
//        blogEsService.pageQuery()

        final Page<Blog> page = blogEsService.pageQuery(2, 9, "mvc");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
        System.out.println(page.getContent().size());
    }
}