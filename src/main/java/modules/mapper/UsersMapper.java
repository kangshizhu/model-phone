package modules.mapper;

import modules.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-16
 */
public interface UsersMapper extends BaseMapper<Users> {

    void add(@Param("users") Users users);
}
