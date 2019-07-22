package org.wlxy.example.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@ApiModel(value = "user" ,description = "用户实体类")
@Data
public class User implements Serializable { //Serializable起到序列化的作用
    @ApiModelProperty(value = "用户名" ,name = "userName")
    @NotEmpty(message = "用户名不能为空")
    @Size(max=12,min=6,message = "用户名长度必须是6-12位")
    private  String userName;
    @ApiModelProperty(value = "用户密码" ,name = "password")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(.{8,12})$",message = "密码必须是包含数字和字母的8到12位")
    private  String password;
    @ApiModelProperty(value = "用户主键" ,name = "id")
    private  int id;

    @ApiModelProperty(value = "用户id" ,name = "roleId")
    @Null(message = "不允许直接修改用户角色")
    private  String roleId;

    @ApiModelProperty(value = "用户email" ,name = "email")
    @Email(message = "邮箱格式不正确")
    private  String email;

    @ApiModelProperty(value = "用户是否被激活" ,name = "isActive")
    private  int isActive;

    @ApiModelProperty(value = "用户收货地址" ,name = "deliveryAddress")
    private  String deliveryAddress;

    @ApiModelProperty(value = "用户头像" ,name = "headPic")
    private  String headPic;

}
