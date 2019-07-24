package org.wlxy.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlxy.example.model.Product;

import java.util.List;
@Mapper
public interface ProductDao {

    public List<Product> getAllProduct(Product product);
    @Select("select * from product where productName=#{productName}")
    public List<Product> getProductByName(String productName);

    @Delete("delete from product where id = #{id}")
    public  int removeProductById(int id);

    public int updateProductById(Product product);

    @Select("select viewNum from product where id=#{id}")
    public int selectProductView(int id);

    @Select("select * from product where id=#{id}")
    public Product getProductById(int id);

}
