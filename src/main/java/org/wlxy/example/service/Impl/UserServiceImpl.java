package org.wlxy.example.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.thymeleaf.util.StringUtils;
import org.wlxy.example.common.EmailUtil;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.UserDao;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import java.util.List;
import java.util.Random;

@Transactional
@Service(value = "UserService")
@CrossOrigin
public class UserServiceImpl implements UserService {
   @Autowired
     UserDao userDao;


    @Cacheable(key = "#p0",value = "users")
    @Override
    public Object getAllUser(PageParam<User> pageParam) {
       System.out.println("走的数据库");
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

    @Override   // @Transactional
    public boolean updateUser(User user) {
        return userDao.updateUser(user)==1;
    }


    @CachePut(value = "users",key = "#p0.id")
    @Override
    public User addUser(User user) {
        user.setRoleId("general");
        user.setIsActive(0);
        userDao.addUser(user);
        return userDao.getUserById(user.getId());
    }

    @Cacheable(key = "#p0",value = "users")
    @Override

    public User getUserById(int id) {
        System.out.print("走的数据库");
        return userDao.getUserById(id);
    }

    @Override
    public User login(String userName, String password) {
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return  null;
        }else {
            User condition = new User();
            condition.setUserName(userName);
            condition.setPassword(password);

            List<User> userList = userDao.getAllUser(condition);
            User user = null;
            if (userList.size() != 0) {
                user = userList.get(0);
            }

            return user;
        }
    }

    @Override
    public User getUserByName(String userName) {

       User user = userDao.getUserByName(userName);
        return user;
    }

    @Override
    public Object getUserByEmail(String email) throws Exception {
           User user = userDao.getUserByEmail(email);
           if (user!=null){
               if(user.getIsActive()==1){
                String password= (new Random().nextInt(900000)+100000)+"mm";
                user.setPassword(password);
                userDao.updateUser(user);
                EmailUtil.sendEmail(password,email);
                return MyRsp.success(null).msg("邮件已发送至你的邮箱，请查收");
               }else {
                   return  MyRsp.error().msg("该账户未激活，请激活后再找回密码！");
               }
           }else {
               return  MyRsp.error().msg("该邮箱未注册有本站账号，请重试!");
           }
    }

    @Override
    public User getUserByEmail1(String email) {
        return userDao.getUserByEmail(email);
    }
}
