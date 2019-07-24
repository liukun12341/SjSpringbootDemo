package org.wlxy.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "product",description = "商品分类实体类")
@Data
public class ProductType {

    @ApiModelProperty(value = "商品分类主键", name="id")
    private int id;

    @ApiModelProperty(value = "商品分类名称" ,name = "productTypeName")
    @NotEmpty(message = "商品分类名称不能为空")
    private String productTypeName;

    @ApiModelProperty(value = "商品点击次数",name = "viewNum")
    private  int viewNum;

    @ApiModelProperty(value = "商品分类的图片" ,name = "productImg")
    private String productImg;
}
