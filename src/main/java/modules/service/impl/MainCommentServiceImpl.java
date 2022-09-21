package modules.service.impl;

import modules.dto.MainCommentDto;
import modules.entity.MainComment;
import modules.mapper.MainCommentMapper;
import modules.service.IMainCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 第一级评论表 服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@Service
public class MainCommentServiceImpl extends ServiceImpl<MainCommentMapper, MainComment> implements IMainCommentService {
    @Resource
    MainCommentMapper mainCommentMapper;


}
