package com.wanandaodao.bigevent.controller;


import com.wanandaodao.bigevent.pojo.Category;
import com.wanandaodao.bigevent.pojo.Result;
import com.wanandaodao.bigevent.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @GetMapping
    public Result<List<Category>> articleCategoryList(){
        //文章分类列表查询
        List<Category> categoryList= articleCategoryService.serchAll();
        return Result.success(categoryList);
    }

    @PostMapping
    public Result addArticleCategory(@RequestBody Category category){
        articleCategoryService.add(category);
        return Result.success();
    }

    @PutMapping
    public Result updateArticleCategory(@RequestBody Category category){
        articleCategoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteArticleCategory(int id){
        articleCategoryService.delete(id);
        return Result.success();
    }
}
