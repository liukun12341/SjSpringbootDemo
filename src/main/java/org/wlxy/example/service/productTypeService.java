package org.wlxy.example.service;


import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.ProductType;

import java.util.List;

public interface productTypeService {
    public Object getAllProductType(PageParam<ProductType> pageParam);

    public  ProductType getProductTypeByName(String productTypeName);

    public boolean removeProductTypeById(int id);

    public boolean updateProductTypeView(int id);

}
