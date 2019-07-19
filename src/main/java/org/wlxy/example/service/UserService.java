package org.wlxy.example.service;


import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.User;



public interface UserService {
    Object getAllUser(PageParam<User> pageParam);
   public boolean removeUser(int id);
   public  boolean updateUser(User user);
   public  User addUser(User user);
   User getUserById(int id);
   User login(String userName,String password);
   User getUserByName(String userName);
   Object getUserByEmail(String email) throws Exception;
   User getUserByEmail1(String email);
}
