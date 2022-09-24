package modules.mapper;

import modules.dto.IdDto;
import modules.entity.MainComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 第一级评论表 Mapper 接口
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
public interface MainCommentMapper extends BaseMapper<MainComment> {
    //添加一级评论次数
    void addThumbs(@Param("mainCommentId") Long mainCommentId);
    //减少一级评论次数
    void updateThumbs(Long mainCommentId);
}
