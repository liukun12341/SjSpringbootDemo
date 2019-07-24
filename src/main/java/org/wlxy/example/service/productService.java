package org.wlxy.example.service;


import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ProductType;

import java.util.List;

public interface productService {
    public Object getAllProduct(PageParam<Product> pageParam);

    public List<Product> getProductByName(String productName);

    public boolean removeProductById(int id);

    public  boolean updateProductById(Product product);

    public boolean updateProductView(int id);

    public Product getProductById(int id);

}
