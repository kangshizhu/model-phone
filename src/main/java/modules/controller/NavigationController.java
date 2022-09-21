package modules.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import modules.dto.IdDto;
import modules.dto.NavigationDto;
import modules.dto.NavigationSelectDto;
import modules.dto.UsersDto;
import modules.entity.Classroom;
import modules.entity.Navigation;
import modules.mapper.NavigationMapper;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  导航栏
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
@Api(tags = "导航栏接口")
@RestController
@RequestMapping("/navigation")
public class NavigationController {


    @Resource
    NavigationMapper navigationMapper;

    @ApiOperation(value = "导航栏删除", notes = "商户导航栏删除根据id")
    @PostMapping(value = "/delele")
    @ResponseBody
    public Result delele(@RequestBody @Valid IdDto idDto) {
        navigationMapper.deleteById(idDto.getId());
        return Result.OK("删除成功");
    }


    @ApiOperation(value = "导航栏修改", notes = "商户导航栏修改根据id")
    @PostMapping(value = "/update")
    @ResponseBody
    public Result update(@RequestBody @Valid Navigation navigation) {
        navigationMapper.updateById(navigation);
        return Result.OK("删除成功");
    }


    @ApiOperation(value = "导航栏查询", notes = "商户导导航栏查询")
    @PostMapping(value = "/select")
    @ResponseBody
    public Result select(@RequestBody @Valid NavigationSelectDto navigationSelectDto) {
        Page<Navigation> page=new Page<>(navigationSelectDto.getPageNo(),navigationSelectDto.getPageSize());
        QueryWrapper<Navigation> QueryWrapper=new QueryWrapper<>();
        IPage<Navigation> navigationList=navigationMapper.selectPage(page,QueryWrapper);
        return Result.OK(navigationList);
    }



}
