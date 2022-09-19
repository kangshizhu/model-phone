package modules.service;

import com.alibaba.fastjson.JSONObject;
import modules.dto.UsersDto;
import modules.dto.WeiXinLoginDTO;
import modules.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-16
 */
public interface IUsersService extends IService<Users> {
    //微信登录
    Result weiXinLogin(WeiXinLoginDTO weiXinLoginDTO);
    //用户登录
    Result<JSONObject> login(UsersDto usersDto);

}
