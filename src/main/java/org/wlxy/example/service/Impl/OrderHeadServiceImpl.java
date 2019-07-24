package org.wlxy.example.service.Impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlxy.example.common.HttpCode;
import org.wlxy.example.common.MyException;
import org.wlxy.example.dao.OrderDetailDao;
import org.wlxy.example.dao.ProductDao;
import org.wlxy.example.dao.ShoppingCarDao;
import org.wlxy.example.model.OrderDetail;
import org.wlxy.example.model.OrderHead;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ShoppingCar;
import org.wlxy.example.service.OrderHeadService;
import org.wlxy.example.dao.OrderHeadDao;
import java.util.List;

@Service("OrderHeadService")
public class OrderHeadServiceImpl  implements OrderHeadService {

    @Autowired
    OrderHeadDao orderHeadDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    ShoppingCarDao shoppingCarDao;

    @Override
    public boolean addOrderHead(OrderHead oderHead) {

        return orderHeadDao.addOrderHead(oderHead)==1?true:false;
    }

    @Override
    public List<OrderHead> selectOrderHeadByUserId(int userId) {
        return orderHeadDao.selectOderHeadByUserId(userId);
    }

    @Override
    public OrderHead pay(List<ShoppingCar> shoppingcarList) {
        //首先判断购物车的长度为不为0，如果为0，返回订单生成失败
        if(shoppingcarList.size()==0){
                throw new MyException(HttpCode.ERROR).msg("生成订单失败，原因购物车数据长度0");
            }

        // 表头信息的商品名称 ，图片，已经用户名 默认为第一个商品的
        int totalProductCount=0;
        String firstProductName=shoppingcarList.get(0).getProductName();
        String firstProductImg=shoppingcarList.get(0).getProductImg();
        double totalPrice=0.0;
        int userId=shoppingcarList.get(0).getUserId();

        // 表单数据初始化
        OrderHead orderHead = new OrderHead();
        Product product;

        double discount;
        double killDiscount;
        double discountTotal=0;
        double killDiscountTotal=0;

        for(ShoppingCar shoppingcar:shoppingcarList){
            product= productDao.getProductById(shoppingcar.getProductId());
            if(product ==null){
                throw  new MyException(HttpCode.ERROR).msg("你下单的商品查不到");
            }
            if(product.getDeserveNum()<shoppingcar.getProductNum()){
                throw new MyException(HttpCode.ERROR).msg("您下单的商品"+shoppingcar.getProductName()+"库存不足！下单失败！");
            }
            // 设置表头里面该用户本次下单的总商品数
            totalProductCount+=shoppingcar.getProductNum();

            // 为2 表示参加 discount等于数据库里面的 不参加则为0
            discount = product.getIsInDiscount()==2?product.getDiscount():0;
            // 为2 表示参加 killDiscount等于数据库里面的 不参加则为0
            killDiscount=product.getIsInKill()==2?product.getKillDiscount():0;

            //计算总折扣
            discountTotal+=discount*shoppingcar.getProductNum();
            killDiscountTotal+=killDiscount*shoppingcar.getProductNum();

            totalPrice+=(product.getNormalPrice()-discount-killDiscount)*shoppingcar.getProductNum();

        }
        //设置表头数据
        orderHead.setFirstProductImg(firstProductImg);
        orderHead.setFirstProductName(firstProductName);
        orderHead.setTotalPrice(totalPrice);
        orderHead.setUserId(shoppingcarList.get(0).getUserId());
        orderHead.setDiscountTotal(discountTotal);
        orderHead.setKillDiscountTotal(killDiscountTotal);
        orderHead.setTotalProductCount(totalProductCount);

        orderHeadDao.addOrderHead(orderHead);
        // 如果订单表头创建成功  需要及时减去所有库存
        for(ShoppingCar shoppingcar:shoppingcarList){

            product=productDao.getProductById(shoppingcar.getProductId());
            if(product==null){
                throw new MyException(HttpCode.ERROR).msg("您下单的商品查询不到");
            }
            //计算新库存
            int newDeserveNum = product.getDeserveNum()-shoppingcar.getProductNum();
            // 减掉库存
            product.setDeserveNum(newDeserveNum);
            // sql update
            boolean flag = productDao.updateProductById(product)==1?true:false;
            if(!flag){
                throw new MyException(HttpCode.ERROR).msg("库存扣取操作失败");
            }
        }

        // 添加订单到数据库
        OrderDetail orderDetail;
        int successInsert=0;
        for(ShoppingCar shoppingcar:shoppingcarList){
            orderDetail = new OrderDetail();
            BeanUtils.copyProperties(shoppingcar,orderDetail);
            orderDetail.setOrderId(orderHead.getId());
            orderDetail.setId(0);
            // sql  add

            successInsert +=(orderDetailDao.addOrderDetail(orderDetail)==1?1:0);

        }

        if(successInsert!=shoppingcarList.size()){
            throw new MyException(HttpCode.ERROR).msg("订单详情数据录入不完整");
        }

        //订单生成成功  需要清空已被结算的购物车的商品
        for(ShoppingCar shoppingcar:shoppingcarList){
           shoppingCarDao.removeShoppingcarById(shoppingcar.getId());
        }
        System.out.println(orderHead.getId());
        List<OrderHead> orderHeadList =  orderHeadDao.selectOderHeadByUserId(orderHead.getId());
        return orderHeadList.get(0);
    }


}
