package com.ve.locker.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiyi
 * @since 2022-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pre_user_level")
@ApiModel(value="Level对象", description="")
public class Level implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer exp=0;

    private Integer yesterdayExp=0;

    private String ipAddress;

    private String ipSource;

    private LocalDateTime signInTime;
}
