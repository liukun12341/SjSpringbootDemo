package org.wlxy.example.service;

import org.wlxy.example.model.OrderHead;
import org.wlxy.example.model.ShoppingCar;

import java.util.List;

public interface OrderHeadService {

    public boolean addOrderHead(OrderHead oderHead);

    public List<OrderHead> selectOrderHeadByUserId(int userId);

    public OrderHead pay(List<ShoppingCar> shoppingCarList);
}
