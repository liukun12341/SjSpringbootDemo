package org.wlxy.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlxy.example.model.OrderHead;

import java.util.List;

@Mapper
public interface OrderHeadDao {

    public int addOrderHead(OrderHead orderHead);

    @Select("select * from orderhead where id=#{id}")
    public List<OrderHead> selectOderHeadByUserId(int id);
}
