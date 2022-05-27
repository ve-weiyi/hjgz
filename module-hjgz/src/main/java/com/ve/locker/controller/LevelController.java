package com.ve.locker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.Level;
import com.ve.locker.service.ILevelService;
import com.ve.locker.util.IpUtils;
import com.ve.locker.util.LogUtil;
import com.ve.locker.utils.TimeUtil;
import com.ve.locker.vo.LevelVO;
import com.ve.locker.vo.PageResult;
import com.ve.locker.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiyi
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/level")
public class LevelController {
    
    @Autowired
    private ILevelService levelService;

    @Resource
    private HttpServletRequest request;

    /**
     * 添加经验等级 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "签到",notes = "")
    @PutMapping("/signIn")
    public Result<?> signIn(Integer id) {
        Level level= levelService.getById(id);
        if(level==null){
            level=new Level();
            level.setId(id);
        }

        if(!TimeUtil.isYesterday(level.getSignInTime(), LocalDateTime.now())){
            return Result.fail(level,"您今天已签到，请不要重复签到哦Σ(oﾟдﾟoﾉ)");
        }

        level.setExp(level.getExp()+3);
        level.setSignInTime(LocalDateTime.now());
        level.setIpAddress(IpUtils.getIpAddress(request));

        LogUtil.println(level.toString());
        boolean status= levelService.saveOrUpdate(level);

        if(status){
            return Result.ok(level,"签到成功！");
        }else{
            return Result.fail(level);
        }
    }

    /**
     * 添加经验等级 增
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加经验等级",notes = "")
    @PutMapping("/add")
    public Result<?> addLevelExp(Integer id,Integer count) {
        Level level= levelService.getById(id);
        if(level==null){
            level=new Level();
            level.setId(id);
        }
        level.setExp(level.getExp()+count);
        LogUtil.println(level.toString());
        boolean status= levelService.saveOrUpdate(level);
        
        if(status){
            return Result.ok(new LevelVO(level));
        }else{
            return Result.fail(new LevelVO(level));
        }
    }

    /**
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取经验等级",notes = "")
    @GetMapping("/get")
    public Result<?> getLevelExp(Integer id) {
        Level level= levelService.getById(id);
        if(level!=null){
            return Result.ok( new LevelVO(level));
        }else{
            return Result.fail("无法找到用户等级信息。");
        }
    }

    /**
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取经验等级列表",notes = "")
    @PostMapping("/list")
    public Result<?> listLevelExp(@RequestBody List<Integer> ids) {

        List<Level> levels;
        if(ids.size()==0){
            levels= levelService.list();
        }else{
            levels= levelService.listByIds(ids);
        }
        List<LevelVO> levelVOS =new ArrayList<>();
        levels.forEach(
                level ->
                        levelVOS.add(new LevelVO(level))
        );
        if(levels.size()!=0){
            return Result.ok(levelVOS);
        }else{
            return Result.fail("无法找到用户等级信息。");
        }
    }

    /**
     *
     * @return {@link Result <>}
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取经验等级排行榜",notes = "")
    @GetMapping("/rankList")
    public Result<PageResult<?>> rankListLevelExp(Integer page) {

        Page<Level> levelPage = new Page<>(page, 10);
        Page<Level> pages = levelService.page(levelPage, new QueryWrapper<Level>()
                .orderByDesc("exp"));
        pages.getRecords();
        List<Level> levels= pages.getRecords();

        List<LevelVO> levelVOS =new ArrayList<>();
        levels.forEach(
                level ->
                        levelVOS.add(new LevelVO(level))
        );
        if(levels.size()!=0){
            return Result.ok(new PageResult<>(levelVOS,levelService.count()));
        }else{
            return Result.fail("无法找到用户等级信息。");
        }
    }
}
