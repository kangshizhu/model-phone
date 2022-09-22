package modules.service;

import modules.dto.CommentAddDto;
import modules.dto.IdDto;
import modules.dto.MainCommentDto;
import modules.entity.MainComment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 第一级评论表 服务类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
public interface IMainCommentService extends IService<MainComment> {
    void addThumbs(CommentAddDto commentAddDto);
    //一级评论点赞接口传main_comment的id
    
}
