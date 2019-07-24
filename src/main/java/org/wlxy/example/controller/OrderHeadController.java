package org.wlxy.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.model.OrderHead;
import org.wlxy.example.model.ShoppingCar;
import org.wlxy.example.service.OrderHeadService;

import java.util.List;

@Api(value = "/OrderHeadController" ,description = "添加订单信息表头的接口")
@RequestMapping("OrderHeadController")
@RestController("OrderHeadController")
@CrossOrigin
public class OrderHeadController {
    @Autowired
    OrderHeadService orderHeadService;

    @PostMapping("/addOrderHead")
    @ApiModelProperty("添加订单表头信息")
    public  Object addOrderHead(@RequestBody OrderHead orderHead){
        return orderHeadService.addOrderHead(orderHead)? MyRsp.success(orderHead).msg("添加成功"):MyRsp.error().msg("添加失败");
    }
    @GetMapping("/selectOderHeadByUserId/{userId}")
    @ApiModelProperty("按用户id查询订单表头信息")
    public  Object selectOderHeadByUserId(@PathVariable("userId") int userId){
        List<OrderHead> orderHeadList = orderHeadService.selectOrderHeadByUserId(userId);
        return orderHeadList!=null? MyRsp.success(orderHeadList).msg("查询成功"):MyRsp.error().msg("查询失败");
    }

    @PostMapping("/pay")
    public Object pay(@RequestBody List<ShoppingCar> shoppingCarList){
           OrderHead orderHead = orderHeadService.pay(shoppingCarList);
        return MyRsp.success(orderHead).msg("添加成功");
    }
}
