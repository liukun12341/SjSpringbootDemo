package org.wlxy.example.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("reg")
@CrossOrigin //用于处理跨域问题 例如vue的端口为8080 而idea项目为8082不加这个注释就不能运行成功
public class registerController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public  Object register(@RequestBody @Valid User user){
        user.setRoleId("general");
        user.setIsActive(0);
        User user1 = userService.getUserByEmail1(user.getEmail());
        if (user1!=null){
            return  MyRsp.error().msg("该邮箱已被占用，不能重复注册！");
        }else {
            return MyRsp.success(userService.addUser(user));
        }
    }
    @PostMapping("/userNameAvailability/{userName}")
    public Object userNameAvailability(@PathVariable("userName") String userName){
        User user = userService.getUserByName(userName);
        return user!=null?MyRsp.success(null).msg("用户名已经被使用"):MyRsp.error().msg("用户名可以使用");
    }
}

