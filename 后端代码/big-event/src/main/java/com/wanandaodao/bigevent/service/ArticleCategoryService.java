package com.wanandaodao.bigevent.service;

import com.wanandaodao.bigevent.pojo.Category;

import java.util.List;

public interface ArticleCategoryService {
    List<Category> serchAll();

    void add(Category category);

    void update(Category category);

    void delete(int id);
}
