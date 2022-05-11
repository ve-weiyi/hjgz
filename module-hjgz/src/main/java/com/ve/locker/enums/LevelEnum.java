package com.ve.locker.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举
 * @author weiyi
 * @date 2022/05/11
 */
@Getter
@AllArgsConstructor
public enum LevelEnum {

    /**
     * 等级1 0~4
     */
    LEVEL_1(1,0,50),
    LEVEL_2(2,50,150),
    LEVEL_3(3,150,500),
    LEVEL_4(4,500,1000),
    LEVEL_5(5,1000,2000),
    LEVEL_6(6,2000,5000),
    LEVEL_7(7,5000,10000),
    LEVEL_8(8,10000,20000),
    LEVEL_9(9,20000,50000),
    LEVEL_10(10,50000,100000),
    LEVEL_0(0,0,0),
    ;

    /**
     * 角色id
     */
    private final Integer level;

    /**
     * 最低经验
     */
    private final Integer lowExp;

    /**
     * 最低经验
     */
    private final Integer nextExp;
}
