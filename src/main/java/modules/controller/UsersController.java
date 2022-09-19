package modules.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import modules.dto.ClassroomDto;
import modules.dto.UsersDto;
import modules.dto.WeiXinLoginDTO;
import modules.entity.Classroom;
import modules.entity.Users;
import modules.mapper.UsersMapper;
import modules.service.IUsersService;
import modules.util.HttpUtil;
import modules.util.RedisUtil;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  用户表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
@Api(tags = "用户登陆接口")
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    IUsersService iUsersService;

    /**
     * 微信免密码登录
     * @param weiXinLoginDTO
     * @return
     */
    @ApiOperation("微信登录")
    @RequestMapping(value = "/weiXinLogin", method = RequestMethod.POST)
    public Result weiXinLogin(@RequestBody @Valid WeiXinLoginDTO weiXinLoginDTO){
        return iUsersService.weiXinLogin(weiXinLoginDTO);
    }


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody @Valid UsersDto usersDto) {
        Result<JSONObject> result=iUsersService.login(usersDto);
        return Result.OK(result);
    }

    @ApiOperation(value = "用户查询", notes = "用户查询")
    @PostMapping(value = "/select")
    @ResponseBody
    public Result select() {
        Users usersAdd=new Users();
        usersAdd.setUsername("xxxx");
        iUsersService.save(usersAdd);
        List<Users> users=iUsersService.list();
        return Result.OK(users);
    }


}
