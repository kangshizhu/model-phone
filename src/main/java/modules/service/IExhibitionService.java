package modules.service;

import modules.dto.ExhibitionIdDto;
import modules.dto.UserIdDto;
import modules.entity.Exhibition;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.vo.ExhibitionReturnVo;
import modules.vo.ExhibitionnVo;
import modules.vo.NumberTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
public interface IExhibitionService extends IService<Exhibition> {


    //小程序作品查询
    List<ExhibitionnVo> selectAll(@Param("userId") Long userId);

    //查询单个小程序所有
    ExhibitionReturnVo selectById(ExhibitionIdDto exhibitionIdDto);

    //小程序作品点赞根据作品id
    NumberTypeVo thumbsById(UserIdDto userIdDto);

    //小程序作品收藏取消收藏根据作品id
    NumberTypeVo followsById(UserIdDto userIdDto);


}
