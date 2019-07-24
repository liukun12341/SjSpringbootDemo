package org.wlxy.example.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlxy.example.dao.OrderDetailDao;
import org.wlxy.example.dao.OrderHeadDao;
import org.wlxy.example.model.OrderDetail;
import org.wlxy.example.model.OrderHead;
import org.wlxy.example.service.OrderDetailService;
import org.wlxy.example.service.OrderHeadService;

import java.util.List;

@Service("OrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) {

        return orderDetailDao.addOrderDetail(orderDetail)==1?true:false;
    }

    @Override
    public List<OrderDetail> selectOrderDetailByUserId(int userId) {
        return orderDetailDao.selectOrderDetailByUserId(userId);
    }
}
