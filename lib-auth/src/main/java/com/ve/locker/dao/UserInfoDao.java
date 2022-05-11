package com.ve.locker.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ve.locker.entity.UserInfo;
import org.springframework.stereotype.Repository;


/**
 * 用户信息
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Repository
public interface UserInfoDao extends BaseMapper<UserInfo> {

}
