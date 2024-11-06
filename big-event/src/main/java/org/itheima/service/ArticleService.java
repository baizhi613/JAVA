package org.itheima.service;

import org.itheima.pojo.Article;
import org.itheima.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
