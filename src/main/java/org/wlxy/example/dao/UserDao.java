package org.wlxy.example.dao;

import org.apache.ibatis.annotations.*;
import org.wlxy.example.model.User;

import java.util.List;

@Mapper
public interface UserDao {

    //@Select("select * from user")
    List<User> getAllUser(User user);
   // @Delete("delete from user where id=#{id}")
    int removeUser(int id);
   // @Update("update user set userName=#{userName},password=#{password},roleId=#{roleId} where id=#{id}")
    int updateUser(User user);
   // @Insert("insert into user(userName,password,roleId) values(#{userName},#{password},#{roleId})")
    int addUser(User user);
    User getUserById(int id);
    @Select("select * from user where userName = #{userName}")
    User getUserByName(String userName);

    @Select("select * from user where email = #{email}")
    User getUserByEmail(String email);
}
