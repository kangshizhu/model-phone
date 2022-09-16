package modules.service;

import com.alibaba.fastjson.JSONObject;
import modules.dto.UsersDto;
import modules.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-16
 */
public interface IUsersService extends IService<Users> {

    Result<JSONObject> queryRuleUser(UsersDto usersDto);
}
