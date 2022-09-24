package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.CommentAddDto;
import modules.dto.IdDto;
import modules.dto.MainCommentDto;
import modules.entity.Exhibition;
import modules.entity.MainComment;
import modules.entity.MainCommentThumbs;
import modules.entity.UsersExhibitonThumbs;
import modules.mapper.MainCommentMapper;
import modules.mapper.MainCommentThumbsMapper;
import modules.service.IMainCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.vo.NumberTypeVo;
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
    @Resource
    MainCommentThumbsMapper mainCommentThumbsMapper;


    //一级评论点赞接口传main_comment的id
    @Override
    public NumberTypeVo addThumbs(CommentAddDto commentAddDto) {
        NumberTypeVo numberTypeVo=new NumberTypeVo();
        //查询是否点赞过
        QueryWrapper<MainCommentThumbs> mainCommentThumbsQueryWrapper=new QueryWrapper<>();
        mainCommentThumbsQueryWrapper.eq("main_comment_id",commentAddDto.getMainCommentId()).eq("main_comment_users_thumbs_id",commentAddDto.getUsersId());
        MainCommentThumbs mainCommentThumbs=mainCommentThumbsMapper.selectOne(mainCommentThumbsQueryWrapper);
        //查询
        QueryWrapper<MainComment> mainCommentQueryWrapper=new QueryWrapper<>();
        mainCommentQueryWrapper.eq("id",commentAddDto.getMainCommentId());
        Integer numbers=mainCommentMapper.selectOne(mainCommentQueryWrapper).getThumbs();
        //没有点赞 开始点赞
        if(mainCommentThumbs==null){
            //添加一级评论次数
            mainCommentMapper.addThumbs(commentAddDto.getMainCommentId());
            //添加一级评论点赞人和一级评论关联表
            MainCommentThumbs mainCommentThumbsAdd=new MainCommentThumbs();
            mainCommentThumbsAdd.setMainCommentId(commentAddDto.getMainCommentId());
            mainCommentThumbsAdd.setMainCommentUsersThumbsId(commentAddDto.getUsersId());
            mainCommentThumbsAdd.setExhibitionId(commentAddDto.getExhibitionId());
            mainCommentThumbsMapper.insert(mainCommentThumbsAdd);
            numberTypeVo.setNumbers(numbers+1);
            numberTypeVo.setType(1);
        //点赞  取消点赞
        }else{
            //减少一级评论次数
            mainCommentMapper.updateThumbs(commentAddDto.getMainCommentId());
            //删除一级评论点赞人和一级评论关联表
            mainCommentThumbsMapper.deleteById(mainCommentThumbs.getId());
            numberTypeVo.setNumbers(numbers-1);
            numberTypeVo.setType(0);
        }

        return numberTypeVo;
    }

}
