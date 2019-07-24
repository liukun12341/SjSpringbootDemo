package org.wlxy.example.service;

import org.wlxy.example.model.OrderDetail;


import java.util.List;

public interface OrderDetailService {

    public boolean addOrderDetail(OrderDetail orderDetail);

    public List<OrderDetail> selectOrderDetailByUserId(int userId);
}
