package org.wlxy.example.model;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.io.Serializable;

@ApiModel(value = "user" ,description = "用户实体类")
@Data
public class User implements Serializable { //Serializable起到序列化的作用
    private  String userName;
    private  String password;
    private  int id;
    private  String roleId;
}
