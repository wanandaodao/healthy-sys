package com.wanandaodao.bigevent.mapper;

import com.wanandaodao.bigevent.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleCategoryMapper {

    @Select("select * from category")
    List<Category> serchAll();

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);


    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias}" +
            ",update_time= #{updateTime} where id = #{id} ")
    void update(Category category);


    @Delete("delete from category where id = #{id}")
    void delete(int id);
}
