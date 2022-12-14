package modules.mapper;

import modules.entity.Exhibition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import modules.vo.ExhibitionReturnVo;
import modules.vo.ExhibitionnVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
public interface ExhibitionMapper extends BaseMapper<Exhibition> {
    //查询所有作品
    List<ExhibitionnVo> selectAll(@Param("usersId")Long usersId);
    //查询单个作品小程序所有
    ExhibitionReturnVo selectByIdReturn(@Param("selectExhibitionId") Long selectExhibitionId,@Param("selectUsersId")Long selectUsersId,@Param("usersId")Long usersId);
    //添加点赞次数
    void thumbsById(@Param("id") Long id);
    //减少点赞次数
    void thumbsByIdUpdate(@Param("id")Long id);
    //添加收藏次数
    void followsById(@Param("id")Long id);
    //减少收藏次数
    void followsByIdUpdate(@Param("id")Long id);
    //添加作品评论数量
    void updateCommentNumbersById(Long exhibitionId);
}
