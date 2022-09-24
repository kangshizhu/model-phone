package modules.mapper;

import modules.entity.MinorComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 第二级评论表 Mapper 接口
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
public interface MinorCommentMapper extends BaseMapper<MinorComment> {
    //添加二级评论次数
    void addThumbs(@Param("minorCommentId") Long minorCommentId);
    //减少二级评论次数
    void updateThumbs(@Param("minorCommentId") Long minorCommentId);
}
