package org.wlxy.example.common;

import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

@RestController
@RequestMapping("login")
@CrossOrigin //用于处理跨域问题 例如vue的端口为8080 而idea项目为8082不加这个注释就不能运行成功
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/userLogin")
    public Object userLogin(@RequestBody User user){
        User u= userService.login(user.getUserName(),user.getPassword());
        return u!=null?MyRsp.success(JwtUtil.sign(user.getUserName(),user.getPassword())):
                MyRsp.error().msg("登录失败");
    }

    @PostMapping("/loadUser/{userName}")
    public  Object loadUser(@PathVariable("userName") String userName){
        User u = userService.getUserByName(userName);
        return u!=null?MyRsp.success(u).msg("返回成功"):MyRsp.error().msg("返回失败");
    }

    @PostMapping("/userSave")
    public Object userSave(@RequestBody User user){
        return userService.updateUser(user)?MyRsp.success(null).msg("修改成功"):MyRsp.error().msg("修改失败");
    }


}
