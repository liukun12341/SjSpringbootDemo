package org.wlxy.example.model;

import lombok.Data;

import java.util.Date;
@Data
public class OrderHead {
    private int id;//主键id',
    private int totalProductCount;//'订单商品总件数',
    private String firstProductName;//订单第一件商品的名称',
    private String firstProductImg;//订单第一件商品的图片',
    private double totalPrice;//订单商品总价（含运费）',
    private int userId;//'用户id',
    private Date createTime;// '订单创建时间',
    private String state;//'订单状态',
    private double discountTotal; //总折扣
    private double killDiscountTotal;//秒杀总折扣

}
