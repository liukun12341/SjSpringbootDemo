package org.wlxy.example.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.ProductTypeDao;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ProductType;
import org.wlxy.example.model.User;
import org.wlxy.example.service.productTypeService;

import java.util.List;

@Service(value = "productTypeService")
public class productTypeServiceImpl implements productTypeService {
   @Autowired
   ProductTypeDao productTypeDao;

    @Override
    public Object getAllProductType(PageParam<ProductType> pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }
        List<ProductType> productTypeList=productTypeDao.getAllProductType(pageParam.getModel());

        PageInfo<ProductType> userPageInfo = new PageInfo<ProductType>(productTypeList);

        return userPageInfo;
    }

    public ProductType getProductTypeByName(String productTypeName){
        return productTypeDao.getProductTypeByName(productTypeName);
    }

    @Override
    public boolean removeProductTypeById(int id) {
        return productTypeDao.removeProductTypeById(id)==1?true:false;
    }

    public boolean updateProductTypeView(int id){

        int i = productTypeDao.selectProductTypeByView(id);
        ProductType productType = new ProductType();
        productType.setId(id);
        productType.setViewNum(++i);
        System.out.println(productType.getViewNum());
        return  productTypeDao.updateProductTypeById(productType)==1;
    }


}
