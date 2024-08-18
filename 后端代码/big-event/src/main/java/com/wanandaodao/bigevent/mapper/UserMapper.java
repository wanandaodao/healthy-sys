package com.wanandaodao.bigevent.mapper;

import com.wanandaodao.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);


    @Insert("insert into user(username, password, create_time, update_time,nickname)" +
            "values(#{username},#{password},now(),now(),'叨叨') ")
    void register(String username,String password);


    @Select("select *from user where username = #{username}")
    User getUserInfoByUsername(String username);

    @Update("update user set nickname = #{nickname},update_time = #{updateTime},email = #{email} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatarUrl},update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl,int id);


    @Update("update user set password = #{password} where id = #{id}")
    void updatePassword(User user);

    @Update("update user set role = 'doctor' where id = #{id}")
    void becomeDoctor(int id);
}
