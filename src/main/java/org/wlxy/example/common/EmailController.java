package org.wlxy.example.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.model.User;
import org.wlxy.example.service.UserService;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmailController {
    @Autowired
    UserService userService;
    @GetMapping("/sendPassword/{email}")
    public  Object sendPassword(@PathVariable("email") String email) throws Exception {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/emailAvailability/{email}")
    public Object userNameAvailability(@PathVariable("email") String email){
                User user =userService.getUserByEmail1(email);
        return user!=null?MyRsp.success(null).msg("用户名已经被使用"):MyRsp.error().msg("用户名可以使用");
    }
}
