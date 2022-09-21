package modules.service.impl;

import modules.entity.MinorComment;
import modules.mapper.MinorCommentMapper;
import modules.service.IMinorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第二级评论表 服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@Service
public class MinorCommentServiceImpl extends ServiceImpl<MinorCommentMapper, MinorComment> implements IMinorCommentService {

}
