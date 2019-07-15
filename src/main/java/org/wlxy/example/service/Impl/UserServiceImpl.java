package org.wlxy.example.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.UserDao;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import java.util.List;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {
   @Autowired
     UserDao userDao;

    @Cacheable(key = "#p0",value = "users")
    @Override
    public Object getAllUser(PageParam<User> pageParam) {
       System.out.println("走的是数据库");

        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }
        List<User> userList=userDao.getAllUser(pageParam.getModel());
        PageInfo<User> userPageInfo = new PageInfo<User>(userList);


        return userPageInfo;
    }
    public boolean removeUser(int id){
        return userDao.removeUser(id)==1;
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user)==1;
    }

    @CachePut(value = "users",key = "#p0.id")
    @Override
    public User addUser(User user) {
        userDao.addUser(user);
        return userDao.getUserById(user.getId());
    }
    @Cacheable(key = "#p0",value = "users")
    @Override
    public User getUserById(int id) {
        System.out.print("走的数据库");
        return userDao.getUserById(id);
    }
}
