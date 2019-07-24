package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.wlxy.example.common.MyRsp;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.ProductType;
import org.wlxy.example.service.productTypeService;


@Api(value = "/ProductTypeController" ,description = "商品分类的接口")
@RequestMapping("productType")
@RestController("ProductTypeController")
@CrossOrigin
public class ProductTypeController {

    @Autowired
    productTypeService productTypeService;

    @ApiOperation(value = "查询所有用户的方法")
    @PostMapping(value = "/getAllProductType")
    public Object getAllProductType(@RequestBody  PageParam<ProductType> pageParam){
        return MyRsp.success(productTypeService.getAllProductType(pageParam));
    }

    @ApiOperation(value = "按商品分类名称查询商品分类的方法")
    @PostMapping(value = "/getAllProductType/{productTypeName}")
    public Object getProductType(@RequestParam("productTypeName") String productTypeName){
        ProductType productType = productTypeService.getProductTypeByName(productTypeName);
        return productType!=null?MyRsp.success(productType).msg("查询成功"):MyRsp.error().msg("查询失败");
    }

    @ApiOperation(value = "按id删除商品分类的方法")
    @PostMapping(value = "/removeProductTypeById/{id}")
    public Object removeProductTypeById(@PathVariable("id") int id){
        return productTypeService.removeProductTypeById(id)?MyRsp.success(null).msg("查询成功"):MyRsp.error().msg("查询失败");
    }


    @ApiModelProperty(value = "按id修改商品浏览量的方法")
    @PostMapping(value = "/updateProductTypeView/{id}")
    public Object updateProductTypeView(@PathVariable("id") int id){
        System.out.println(id);
        return productTypeService.updateProductTypeView(id)?MyRsp.success(null).msg("修改成功"):MyRsp.error().msg("修改失败");
    }






}
