package org.itheima.controller;

import jakarta.validation.Valid;
import org.itheima.pojo.Article;
import org.itheima.pojo.PageBean;
import org.itheima.pojo.Result;
import org.itheima.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Valid Article article){
        articleService.add(article);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ){
        PageBean<Article> pb=articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);
    }
}
