package org.wlxy.example.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wlxy.example.model.ProductType;

import java.util.List;

@Mapper
public interface ProductTypeDao {

    public List<ProductType> getAllProductType(ProductType productType);

    @Select("select * from producttype where productTypeName=#{productTypeName}")
    public ProductType getProductTypeByName(String productTypeName);

    @Delete("delete from producttype where id = #{id}")
    public  int removeProductTypeById(int id);

    @Select("select viewNum from producttype where id = #{id}")
    public  int selectProductTypeByView(int id);

    public  int updateProductTypeById(ProductType productType);




}
