package com.ve.locker.util;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Description 配置文件提取工具类
 * @Author weiyi
 * @Date 2022/3/8
 */
public class YamlUtils {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String path;


}
