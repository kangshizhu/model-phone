package modules.service.impl;

import modules.entity.MainCommentThumbs;
import modules.mapper.MainCommentThumbsMapper;
import modules.service.IMainCommentThumbsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 一级评论点赞人和一级评论关联表 服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-23
 */
@Service
public class MainCommentThumbsServiceImpl extends ServiceImpl<MainCommentThumbsMapper, MainCommentThumbs> implements IMainCommentThumbsService {

}
