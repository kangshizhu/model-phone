package modules.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import modules.dto.IdDto;
import modules.dto.MinorCommentDto;
import modules.entity.MinorComment;
import modules.mapper.ExhibitionMapper;
import modules.mapper.NavigationMapper;
import modules.service.IMinorCommentService;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 第二级评论表 前端控制器
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/minorComment")
public class MinorCommentController {

    @Resource
    IMinorCommentService iMinorCommentService;
    @Resource
    ExhibitionMapper exhibitionMapper;

    @ApiOperation(value = "二级评论添加接口", notes = "二级评论添加接口")
    @PostMapping(value = "/add")
    @ResponseBody
    public Result add(@RequestBody @Valid MinorCommentDto minorCommentDto) {
        MinorComment minorComment=new MinorComment();
        BeanUtils.copyProperties(minorCommentDto,minorComment);
        minorComment.setThumbs(0);
        iMinorCommentService.save(minorComment);
        //添加作品评论数量
        exhibitionMapper.updateCommentNumbersById(minorCommentDto.getExhibitionId());
        return Result.OK("添加成功");
    }


}
