package com.ve.locker.util;

import com.ve.locker.dto.UserDetailDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * 用户工具类
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Component
public class UserUtils {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailDTO getLoginUser() {
        return (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 验证此id是否为当前拥有持有
     *
     * @return 用户登录信息
     */
    public static boolean checkUser(Integer uid) {
        return getUserId().equals(uid);
    }

    /**
     * 获取当前登录操作员id
     * @return
     */
    public static Integer getUserId(){
        UserDetailDTO user=(UserDetailDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
