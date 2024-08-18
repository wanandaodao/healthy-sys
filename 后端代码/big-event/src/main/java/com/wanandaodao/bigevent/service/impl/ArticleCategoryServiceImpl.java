package com.wanandaodao.bigevent.service.impl;

import com.wanandaodao.bigevent.mapper.ArticleCategoryMapper;
import com.wanandaodao.bigevent.pojo.Category;
import com.wanandaodao.bigevent.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Override
    public List<Category> serchAll() {
        return articleCategoryMapper.serchAll();
    }

    @Override
    public void add(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(1);
        articleCategoryMapper.add(category);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        articleCategoryMapper.update(category);
    }

    @Override
    public void delete(int id) {
        articleCategoryMapper.delete(id);
    }
}
