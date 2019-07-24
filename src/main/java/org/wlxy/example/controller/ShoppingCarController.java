package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.ShoppingCar;
import org.wlxy.example.service.Impl.ShoppingCarServiceImpl;

import java.util.List;


@Api(value = "/ShoppingCarController" ,description = "购物车的接口")
@RequestMapping("shoppingCar")
@RestController("ShoppingCarController")
@CrossOrigin
public class ShoppingCarController {
    @Autowired
    ShoppingCarServiceImpl shoppingCarService;

    @ApiModelProperty("按用户id查购物车")
    @PostMapping("/getShoppingCarById/{userId}")
    public Object getShoppingCarById(@PathVariable("userId") int userId){
        List<ShoppingCar> shoppingCarList =shoppingCarService.getAllShoppingCar(userId);
        return shoppingCarList.size()>=1? MyRsp.success(shoppingCarList).msg("查询成功"):MyRsp.error().msg("查询失败");

    }

    @ApiModelProperty("添加购物车")
    @PostMapping("/addShoppingCar")
    public Object addShoppingCar(@RequestBody ShoppingCar shoppingCar){
        int i = shoppingCarService.addShoppingCar(shoppingCar);
        return i==1? MyRsp.success(null).msg("添加成功"):MyRsp.error().msg("添加失败");

    }





}
