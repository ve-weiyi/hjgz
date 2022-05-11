package com.ve.locker.vo;

import com.ve.locker.dto.UserDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * QQ登录
 *
 * @author yezhqiu
 * @date 2021/06/14
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "登录返回信息")
public class LoginVO {

    /**
     * token head
     */
    @NotBlank(message = "tokenHead不能为空")
    @ApiModelProperty(name = "tokenHead", value = "tokenHead", required = true, dataType = "String")
    private String tokenHead;
    /**
     * accessToken
     */
    @NotBlank(message = "accessToken不能为空")
    @ApiModelProperty(name = "token", value = "accessToken", required = true, dataType = "String")
    private String accessToken;
//
//    private String publicKey;
//
//    private String privateKey;
//
    @NotBlank(message = "UserDetailDTO不能为空")
    @ApiModelProperty(name = "userDetailDTO", value = "userDetailDTO", required = true, dataType = "UserDetailDTO")
    private UserDetailDTO userDetailDTO;
}
