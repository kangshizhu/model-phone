package modules.service;

import modules.dto.MinorCommentDto;
import modules.dto.MinorCommentThumbsDto;
import modules.entity.MinorComment;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.vo.NumberTypeVo;

/**
 * <p>
 * 第二级评论表 服务类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
public interface IMinorCommentService extends IService<MinorComment> {
    //二级点赞接口传minor_comment的id
    NumberTypeVo addThumbs(MinorCommentThumbsDto minorCommentThumbsDto);

}
