package org.wlxy.example.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.wlxy.example.dao.ShoppingCarDao;
import org.wlxy.example.model.ShoppingCar;
import org.wlxy.example.service.shoppingCarService;

import java.util.List;


@Service(value = "shoppingCarService")
public class ShoppingCarServiceImpl implements shoppingCarService {
   @Autowired
    ShoppingCarDao shoppingCarDao;

    @Override
    public int addShoppingCar(ShoppingCar shoppingCar) {
        return shoppingCarDao.addShoppingCar(shoppingCar);
    }

    @Override
    public List<ShoppingCar> getAllShoppingCar(int userId) {
        return shoppingCarDao.getShoppingCarById(userId);
    }
}
