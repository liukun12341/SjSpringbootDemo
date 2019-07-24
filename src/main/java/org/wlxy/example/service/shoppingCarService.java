package org.wlxy.example.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.wlxy.example.dao.ShoppingCarDao;
import org.wlxy.example.model.ShoppingCar;

import java.util.List;

public interface shoppingCarService {


    public int addShoppingCar(ShoppingCar shoppingCar);

    public List<ShoppingCar> getAllShoppingCar(int userId);

}
