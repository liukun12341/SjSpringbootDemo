package org.wlxy.example.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDetail {

    private int id;//'主键id',
    private int productId;//商品id',
    private int orderId;//订单表头id',
    private int userId;//'用户id',
    private String productName;// '商品名称',
    private double normalPrice;//'正常情况时的价格',
    private double discount;//'折扣价格',
    private int isInDiscount;//是否参与折扣活动 2参加 1不参加',
    private int typeId;//'商品分类id',
    private Date createTime;//上架时间',
    private int isInKill;//'是否参与秒杀活动 2参加 1不参加',
    private double killDiscount;//'秒杀的折扣',
    private String productImg;//商品图片',
    private int viewNum;//浏览量',
    private int deserveNum;//库存量',
    private String describes;//商品描述',
    private int orderCount;//下单数',
    private double deliveryPrice;//快递费用',
    private String deliveryPlace;//发货地址',
    private int productNum;//商品购买件数',

}
