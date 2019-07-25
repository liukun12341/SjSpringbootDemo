package org.wlxy.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ShoppingCar;

import java.util.List;

@Mapper
public interface ShoppingCarDao {

//    public List<Product> getAllProduct(Product product);
//    @Select("select * from product where productName=#{productName}")
//    public List<Product> getProductByName(String productName);

//    @Delete("delete from product where id = #{id}")
//    public  int removeProductById(int id);
//
//    public int updateShoppingById(ShoppingCar shoppingCar);
    public  int addShoppingCar(ShoppingCar shoppingCar);
//    @Select("select viewNum from product where id=#{id}")
//    public int selectProductView(int id);

    @Select("select * from shoppingcar where userId=#{userId}")
    public List<ShoppingCar> getShoppingCarById(int userId);

//    @Select("select * from shoppingcar where productId=#{productId}")
//    public List<ShoppingCar> getShoppingCarByProductId(int productId);
    @Delete("Delete  from shoppingcar where id=#{id}" )
    void removeShoppingcarById(int id);
}
