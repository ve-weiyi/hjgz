package com.ve.locker.service;

import com.ve.locker.vo.WebsiteConfigVO;

/**
 * @Description hello word!
 * @Author weiyi
 * @Date 2022/3/8
 */
public interface AppInfoService {

    /**
     * 获取网站配置
     *
     * @return {@link WebsiteConfigVO} 网站配置
     */
    WebsiteConfigVO getWebsiteConfig();
}
