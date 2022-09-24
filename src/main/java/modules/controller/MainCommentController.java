package modules.controller;


import io.swagger.annotations.ApiOperation;
import modules.dto.CommentAddDto;
import modules.dto.IdDto;
import modules.dto.MainCommentDto;
import modules.dto.UserIdAddDto;
import modules.entity.Exhibition;
import modules.entity.MainComment;
import modules.mapper.ExhibitionMapper;
import modules.mapper.MainCommentMapper;
import modules.service.IMainCommentService;
import modules.vo.NumberTypeVo;
import modules.vo.Result;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 第一级评论表 前端控制器
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/mainComment")
public class MainCommentController {

    @Resource
    IMainCommentService iMainCommentService;
    @Resource
    ExhibitionMapper exhibitionMapper;

    @ApiOperation(value = "一级评论添加接口 ", notes = "一级评论添加接口 同时根据exhibitionId添加作品评论数量")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody @Valid MainCommentDto mainCommentDto) {
        MainComment mainComment=new MainComment();
        BeanUtils.copyProperties(mainCommentDto,mainComment);
        mainComment.setThumbs(0);
        iMainCommentService.save(mainComment);
        //添加作品评论数量
        exhibitionMapper.updateCommentNumbersById(mainCommentDto.getExhibitionId());
        return Result.OK("添加成功");
    }

    @ApiOperation(value = "一级评论点赞接口传main_comment的id", notes = "一级评论点赞接口 传main_comment的id 同时根据id添加点赞数量")
    @PostMapping(value = "/addThumbs")
    @ResponseBody
    public Result addThumbs(@RequestBody @Valid CommentAddDto commentAddDto) {
        NumberTypeVo numberTypeVo=iMainCommentService.addThumbs(commentAddDto);
        return Result.OK(numberTypeVo);
    }


}
