package modules.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.UsersDto;
import modules.entity.Users;
import modules.mapper.UsersMapper;
import modules.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.util.CommonConstant;
import modules.util.JwtUtil;
import modules.util.PasswordUtil;
import modules.util.RedisUtil;
import modules.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-16
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {
    @Resource
    UsersMapper usersMapper;
    @Autowired
    private RedisUtil redisUtil;
    //用户登录后返回token和用户信息
    @Override
    public Result<JSONObject> queryRuleUser(UsersDto usersDto) {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        //String userpassword = PasswordUtil.encrypt("cloud", password,salt);
        queryWrapper.eq("phone",usersDto.getPhone());
        Users userOne=usersMapper.selectOne(queryWrapper);
        Result<JSONObject> result=new Result<JSONObject>();
        Long date = new Date(System.currentTimeMillis() + JwtUtil.EXPIRE_DATE).getTime();
        // 生成token
        String token = JwtUtil.sign(usersDto.getPhone(), usersDto.getPassword());
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, userOne);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, date);
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        obj.put("userInfo", userOne);
        result.setResult(obj);
        return result;
    }
}
