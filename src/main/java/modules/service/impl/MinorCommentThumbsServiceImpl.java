package modules.service.impl;

import modules.entity.MinorCommentThumbs;
import modules.mapper.MinorCommentThumbsMapper;
import modules.service.IMinorCommentThumbsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 二级评论点赞人和二级评论关联表 服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-23
 */
@Service
public class MinorCommentThumbsServiceImpl extends ServiceImpl<MinorCommentThumbsMapper, MinorCommentThumbs> implements IMinorCommentThumbsService {

}
