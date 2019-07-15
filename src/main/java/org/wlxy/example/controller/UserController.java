package org.wlxy.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.HttpCode;
import org.wlxy.example.common.MyException;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

@Api(value = "/user" ,description = "用户类的接口")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "查询所有用户的方法")
    @PostMapping(value = "/getAll" )
    public Object getAll(@RequestBody PageParam<User> pageParam){
       // return userService.getAllUser()? MyRsp.success("查询成功"):MyRsp.error().setMsg("查询失败");
      // return  MyRsp.success(userService.getAllUser());
       return MyRsp.success(userService.getAllUser(pageParam));
    }
    @Cacheable(key = "#p0.id",value = "uses")
    @ApiOperation(value = "添加用户的方法")
    @PostMapping(value = "addUser")
    public Object addUser(@RequestBody @ApiParam("要添加的用户信息") User user){

        return  userService.addUser(user)!=null?MyRsp.success(null):MyRsp.error().msg("添加失败");

    }
    @ApiOperation(value = "按id移除用户的方法")
   @RequestMapping(value = "/remove/{id}" ,method = RequestMethod.GET)
   //@PathVariable("userName") 是把上面value = "/remove/{userName}"路径中的userName映射到后面的形参 String userName 中去
   public  Object remove(@PathVariable("id") @ApiParam(value = "需要移除用户的ID") int id){
    return userService.removeUser(id)?MyRsp.success(null):MyRsp.error().msg("查询失败");
   }
    @ApiOperation(value = "更新用户信息的方法")
   @RequestMapping(value = "/update" ,method = RequestMethod.POST)
   //@RequestBody User user 将前端传过来的json数据映射成参数
   public  Object update(@RequestBody @ApiParam("要更新的用户信息") User user){
       System.out.println(user.getUserName());
    return  userService.updateUser(user)?MyRsp.success(null):MyRsp.error().msg("修改失败");
   }

    @ApiOperation(value = "根据ID查询用户信息的方法")
    @GetMapping(value = "/getUserById/{id}" )
    //@RequestBody User user 将前端传过来的json数据映射成参数
    public  Object getUserById(@PathVariable @ApiParam("查询用户的ID") int id){
         User user=userService.getUserById(id);
        return  user!=null?MyRsp.success(user):MyRsp.wrapper(new MyException(HttpCode.ERROR));
    }



}
