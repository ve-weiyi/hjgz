package com.ve.locker.vo;

/**
 * @Description create for hjgz .
 * @Author weiyi
 * @Date 2022/5/11
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ve.locker.entity.Level;
import com.ve.locker.enums.LevelEnum;
import com.ve.locker.util.LogUtil;
import com.ve.locker.utils.LocalDateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

/**
 * QQ登录
 * @author yezhqiu
 * @date 2021/06/14
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "登录返回信息")
public class LevelVO {

    private Integer id;

    private Integer exp=0;
    private Integer yesterdayExp=0;
    private Integer isSignedIn=0;

    private Integer rank=1;

    private Integer nextLevel=50;


    public LevelVO(Level level) {
        this.id = level.getId();
        this.exp = level.getExp();
        this.yesterdayExp=level.getYesterdayExp();
        int exp = level.getExp();

        if (exp >= LevelEnum.LEVEL_10.getLowExp()) {
            rank = LevelEnum.LEVEL_10.getLevel();
            nextLevel=LevelEnum.LEVEL_10.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_9.getLowExp()) {
            rank = LevelEnum.LEVEL_9.getLevel();
            nextLevel=LevelEnum.LEVEL_9.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_8.getLowExp()) {
            rank = LevelEnum.LEVEL_8.getLevel();
            nextLevel=LevelEnum.LEVEL_8.getNextExp();

        } else if (exp >= LevelEnum.LEVEL_7.getLowExp()) {
            rank = LevelEnum.LEVEL_7.getLevel();
            nextLevel=LevelEnum.LEVEL_7.getNextExp();

        } else if (exp >= LevelEnum.LEVEL_6.getLowExp()) {
            rank = LevelEnum.LEVEL_6.getLevel();
            nextLevel=LevelEnum.LEVEL_6.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_5.getLowExp()) {
            rank = LevelEnum.LEVEL_5.getLevel();
            nextLevel=LevelEnum.LEVEL_5.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_4.getLowExp()) {
            rank = LevelEnum.LEVEL_4.getLevel();
            nextLevel=LevelEnum.LEVEL_4.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_3.getLowExp()) {
            rank = LevelEnum.LEVEL_3.getLevel();
            nextLevel=LevelEnum.LEVEL_3.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_2.getLowExp()) {
            rank = LevelEnum.LEVEL_2.getLevel();
            nextLevel=LevelEnum.LEVEL_2.getNextExp();

        }else if (exp >= LevelEnum.LEVEL_1.getLowExp()) {
            rank = LevelEnum.LEVEL_1.getLevel();
            nextLevel=LevelEnum.LEVEL_1.getNextExp();

        }else {
            rank = LevelEnum.LEVEL_0.getLevel();
            nextLevel=LevelEnum.LEVEL_0.getNextExp();
        }

//        String signDay=level.getSignInTime().toString();
//        String nowDay=LocalDateTime.now().toString();
//        LogUtil.println(signDay.substring(0,10));
//        LogUtil.println(nowDay.substring(0,10));
        if(level.getSignInTime().toString().substring(0, 10).equals(LocalDateTime.now().toString().substring(0, 10)))
        {
            this.isSignedIn=1;
        }else{
            this.isSignedIn=0;
        }
    }
}