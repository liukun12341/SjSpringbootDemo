package org.wlxy.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.systemConfig.SystemConfig;
import org.wlxy.example.model.User;

//@RestController 用这个不用再在每个方法上面添加    @ResponseBody
@RestController
@SpringBootApplication
//@EnableScheduling
@MapperScan({"org.wlxy.example.dao","org.wlxy.example.task"})
public class ExampleApplication {

//    @Value(value = "${app.info}")
//    String info;
    @Autowired
    SystemConfig systemConfig;
    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String helloWorld(User user){
        return systemConfig.toString();
    }


}
