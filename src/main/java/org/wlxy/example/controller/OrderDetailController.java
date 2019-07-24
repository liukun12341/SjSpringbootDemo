package org.wlxy.example.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.model.OrderDetail;
import org.wlxy.example.model.OrderHead;
import org.wlxy.example.service.OrderDetailService;
import org.wlxy.example.service.OrderHeadService;

import java.util.List;

@Api(value = "/OrderDetailController" ,description = "添加订单详细信息的接口")
@RequestMapping("OrderDetailController")
@RestController("OrderDetailController")
@CrossOrigin
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/addOrderDetail")
    @ApiModelProperty("添加订单表头信息")
    public  Object addOrderDetail(@RequestBody OrderDetail orderDetail){
        return orderDetailService.addOrderDetail(orderDetail)? MyRsp.success(orderDetail).msg("添加成功"):MyRsp.error().msg("添加失败");
    }
    @PostMapping("/selectOderDetailByUserId/{userId}")
    @ApiModelProperty("按用户id查询订单表头信息")
    public  Object selectOderDetailByUserId(@PathVariable("userId") int userId){
        List<OrderDetail> orderDetailListList = orderDetailService.selectOrderDetailByUserId(userId);
        System.out.println(orderDetailListList.size());
        return orderDetailListList.size()>0? MyRsp.success(orderDetailListList).msg("查询成功"):MyRsp.error().msg("查询失败");
    }
}
