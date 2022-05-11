package com.ve.locker.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ve.locker.entity.Level;
import com.ve.locker.service.ILevelService;
import com.ve.locker.util.LogUtil;
import com.ve.locker.vo.LevelVO;
import com.ve.locker.vo.PageResult;
import com.ve.locker.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/list")
    public Result<PageResult<?>> listLevelExp(Integer page) {

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
