package org.wlxy.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wlxy.example.common.MyRsp;
import org.wlxy.example.common.PageParam;
import org.wlxy.example.model.Product;
import org.wlxy.example.model.ProductType;
import org.wlxy.example.service.productService;
import org.wlxy.example.service.productTypeService;

import java.util.List;


@Api(value = "/ProductController" ,description = "商品的接口")
@RequestMapping("product")
@RestController("ProductController")
@CrossOrigin
public class ProductController {

    @Autowired
    productService productService;

    @ApiOperation(value = "查询所有商品的方法")
    @PostMapping(value = "/getAllProduct")
    public Object getAllProduct(@RequestBody  PageParam<Product> pageParam){
        return MyRsp.success(productService.getAllProduct(pageParam));
    }

    @ApiOperation(value = "按商品名称查询商品的方法")
    @PostMapping(value = "/getAllProduct/{productName}")
    public List<Product> getProduct(@PathVariable("productName") String productName){
        List<Product> product = productService.getProductByName(productName);
        return product;
    }

    @ApiOperation(value = "按id删除商品的方法")
    @PostMapping(value = "/removeProductById/{id}")
    public Object removeProductById(@PathVariable("id") int id){
        return productService.removeProductById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

    @ApiModelProperty(value = "按id修改商品浏览量的方法")
    @PostMapping(value = "/updateProductViewById/{id}")
    public Object updateProductViewById(@PathVariable("id") int id){
    return productService.updateProductView(id)?MyRsp.success(null).msg("修改成功"):MyRsp.error().msg("修改失败");
    }

    @ApiModelProperty(value = "按ID查询商品信息")
    @PostMapping(value = "/getProductById/{id}")
    public Object getProductById(@PathVariable("id") int id){
        return productService.getProductById(id);
    }




}
