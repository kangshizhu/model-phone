package modules.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import modules.dto.ExhibitionDto;
import modules.dto.IdDto;
import modules.dto.UserIdAddDto;
import modules.dto.UserIdDto;
import modules.entity.Exhibition;
import modules.mapper.ExhibitionMapper;
import modules.service.IExhibitionService;
import modules.vo.ExhibitionReturnVo;
import modules.vo.ExhibitionnVo;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  作品展示
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
@Api(tags = "作品展示接口")
@RestController
@RequestMapping("/exhibition")
public class ExhibitionController {

    @Resource
    ExhibitionMapper exhibitionMapper;
    @Resource
    IExhibitionService iExhibitionService;

    @ApiOperation(value = "小程序作品添加", notes = "小程序作品添加")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody @Valid ExhibitionDto exhibitionDto) {
        Exhibition exhibition=new Exhibition();
        //设置点赞关注数量
        BeanUtils.copyProperties(exhibitionDto,exhibition);
        exhibition.setFollows(0);
        exhibition.setThumbs(0);
        exhibition.setCommentNumbers(0);
        exhibition.setExamineType(0);
        exhibitionMapper.insert(exhibition);
        return Result.OK("添加成功");
    }

    @ApiOperation(value = "小程序作品删除", notes = "作品删除根据id")
    @PostMapping(value = "/delete")
    @ResponseBody
    public Result delete(@RequestBody IdDto idDto) {
        exhibitionMapper.deleteById(idDto.getId());
        return Result.OK("删除成功");
    }

    @ApiOperation(value = "查询所有小程序作品", notes = "查询所有小程序作品查询关联users表")
    @PostMapping(value = "/select")
    @ResponseBody
    public Result select(@RequestBody UserIdAddDto userIdAddDto) {
        List<ExhibitionnVo> exhibitionnVoList=iExhibitionService.selectAll(userIdAddDto.getUsersId());
        return Result.OK(exhibitionnVoList);
    }

    @ApiOperation(value = "查询单个小程序所有传exhibition的id", notes = "查询单个小程序所有 关联一级评论表main_comment和二级评论表minor_comment")
    @PostMapping(value = "/selectById")
    @ResponseBody
    public Result selectById(@RequestBody IdDto idDto) {
        List<ExhibitionReturnVo> list=iExhibitionService.selectById(idDto.getId());
        return Result.OK(list);
    }

    @ApiOperation(value = "小程序作品点赞取消点赞", notes = "小程序作品点赞取消点赞根据作品id ")
    @PostMapping(value = "/thumbsById")
    @ResponseBody
    public Result thumbsById(@RequestBody UserIdDto userIdDto) {
        Integer thumbs=iExhibitionService.thumbsById(userIdDto);
        return Result.OK(thumbs);
    }

    @ApiOperation(value = "小程序作品收藏取消收藏", notes = "小程序作品收藏取消收藏根据作品id ")
    @PostMapping(value = "/followsById")
    @ResponseBody
    public Result followsById(@RequestBody UserIdDto userIdDto) {
        Integer follows=iExhibitionService.followsById(userIdDto);
        return Result.OK(follows);
    }

}
