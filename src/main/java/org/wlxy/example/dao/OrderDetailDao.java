package org.wlxy.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlxy.example.model.OrderDetail;

import java.util.List;

@Mapper
public interface OrderDetailDao {

    public int addOrderDetail(OrderDetail orderDetail);

    @Select("select * from orderdetail where userId=#{userId}")
    public List<OrderDetail> selectOrderDetailByUserId(int userId);
}
