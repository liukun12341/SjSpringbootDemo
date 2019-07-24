package org.wlxy.example.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.dao.ProductDao;
import org.wlxy.example.dao.ProductTypeDao;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ProductType;
import org.wlxy.example.service.productService;
import org.wlxy.example.service.productTypeService;

import java.util.List;

@Service(value = "productService")
public class productServiceImpl implements productService {
   @Autowired
    ProductDao productDao;

    @Override
    public Object getAllProduct(PageParam<Product> pageParam) {
        PageHelper.startPage(pageParam.getPageNum(),pageParam.getPageSize());
        for(int i=0;i<pageParam.getOrderParams().length;i++){
            PageHelper.orderBy(pageParam.getOrderParams()[i]);
        }
        List<Product> productList=productDao.getAllProduct(pageParam.getModel());

        PageInfo<Product> userPageInfo = new PageInfo<Product>(productList);

        return userPageInfo;
    }

    public List<Product> getProductByName(String productName){
        return productDao.getProductByName(productName);
    }

    @Override
    public boolean removeProductById(int id) {
        return productDao.removeProductById(id)==1?true:false;
    }

    @Override
    public boolean updateProductById(Product product) {
        return productDao.updateProductById(product)==1;
    }

    @Override
    public boolean updateProductView(int id) {
        int i = productDao.selectProductView(id);
        Product product = new Product();
        product.setId(id);
        product.setViewNum(++i);
        return productDao.updateProductById(product)==1;
    }

    public Product getProductById(int id){
        return productDao.getProductById(id);
    }

}
