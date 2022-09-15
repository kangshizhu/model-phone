package modules.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import modules.dto.ClassroomDto;
import modules.entity.Classroom;
import modules.mapper.ClassroomMapper;
import modules.vo.Result;
import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-15
 */
@Api(tags = "用户接口")
@Log4j2
@RestController
@RequestMapping("/Classroom")

public class ClassroomController {
    @Resource
    ClassroomMapper classroomMapper;

    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @PostMapping(value = "/queryUser")
    @ResponseBody
    public Result queryRuleUser(@RequestBody ClassroomDto classroomDto) {
        Classroom classroom=new Classroom();
        classroom.setUsernames("111");
        classroomMapper.insert(classroom);
        return Result.OK("c");
    }

    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @PostMapping(value = "/update")
    @ResponseBody
    public Result update() {
        Classroom classroom=new Classroom();
        classroom.setUsernames("222");
        classroom.setId(28);
        classroomMapper.updateById(classroom);
        return Result.OK("c");
    }

    @ApiOperation(value = "获取用户", notes = "根据id查询用户信息")
    @PostMapping(value = "/select")
    @ResponseBody
    public Result select(@RequestBody ClassroomDto classroomDto) {
        Classroom classroom=classroomMapper.selectById(28);

        return Result.OK(classroom);
    }
}
