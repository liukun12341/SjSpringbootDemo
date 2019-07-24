package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@ApiModel("商品实体类")
@Data
public class Product {
    private int id;  //'主键id',
    private String productName;//商品名称'
    private double normalPrice;// '正常情况时的价格',
    private double discount;// '0',
    private int isInDiscount; // '是否参与折扣活动 1参加 0不参加',
    private int typeId;// '商品分类id',
    private Date createTime;//'上架时间',
    private int isInKill;//是否参与秒杀活动 1参加 0不参加',
    private double killDiscount;// D秒杀的折扣',
    private String productImg; //商品图片',
    private int viewNum; //'浏览量',
    private int deserveNum; //库存量',
    private String describes; //商品描述
    private int orderCount;
    private double deliveryPrice;
    private String deliveryPlace;//发货地址,

}
