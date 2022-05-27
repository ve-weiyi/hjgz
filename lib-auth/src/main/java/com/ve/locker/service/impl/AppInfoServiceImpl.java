package com.ve.locker.service.impl;

import com.alibaba.fastjson.JSON;
import com.ve.locker.dao.AppInfoConfigDao;
import com.ve.locker.service.AppInfoService;
import com.ve.locker.service.RedisService;
import com.ve.locker.vo.WebsiteConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.ve.locker.constant.RedisPrefixConst.WEBSITE_CONFIG;

/**
 * @Description hello word!
 * @Author weiyi
 * @Date 2022/3/8
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private AppInfoConfigDao appInfoConfigDao;

    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO websiteConfigVO;
        // 获取缓存数据
        Object websiteConfig = redisService.get(WEBSITE_CONFIG);
        if (Objects.nonNull(websiteConfig)) {
            websiteConfigVO = JSON.parseObject(websiteConfig.toString(), WebsiteConfigVO.class);
        } else {
            // 从数据库中加载
            String config = appInfoConfigDao.selectById(1).getConfig();
            websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
            redisService.set(WEBSITE_CONFIG, config);
        }
        return websiteConfigVO;
    }

}
