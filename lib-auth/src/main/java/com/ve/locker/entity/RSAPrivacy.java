package com.ve.locker.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description hello word!
 * @Author weiyi
 * @Date 2022/1/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RSA隐私类", description="")
public class RSAPrivacy {
    @ApiModelProperty(value = "加密模式")
    @TableField(exist = false)//告诉mybatis plus 数据库中没有这个自定义的字段
    public Boolean isEncrypt=true;
}
