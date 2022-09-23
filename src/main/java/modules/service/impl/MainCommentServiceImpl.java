package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.CommentAddDto;
import modules.dto.IdDto;
import modules.dto.MainCommentDto;
import modules.entity.Exhibition;
import modules.entity.MainComment;
import modules.entity.UsersExhibitonThumbs;
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



    //一级评论点赞接口传main_comment的id
    @Override
    public void addThumbs(CommentAddDto commentAddDto) {
//        //查询是否点赞过
//        QueryWrapper<UsersExhibitonThumbs> usersExhibitonThumbsQueryWrapper=new QueryWrapper<>();
//        usersExhibitonThumbsQueryWrapper.eq("users_id",userIdDto.getUsersId()).eq("exhibiton_id",userIdDto.getExhibitionId());
//        UsersExhibitonThumbs usersExhibitonThumbs=usersExhibitonThumbsMapper.selectOne(usersExhibitonThumbsQueryWrapper);
//
//        //获取多少点赞次数并返回
//        QueryWrapper<Exhibition> ExhibitionQueryWrapper=new QueryWrapper<>();
//        ExhibitionQueryWrapper.eq("id",userIdDto.getExhibitionId());
//        Integer thumbs=exhibitionMapper.selectOne(ExhibitionQueryWrapper).getThumbs();
//        //没点赞则添加数据 点赞则取消点赞
//        if(usersExhibitonThumbs==null){
//
//            //添加点赞次数
//            exhibitionMapper.thumbsById(userIdDto.getExhibitionId());
//            //添加关联数据
//            UsersExhibitonThumbs usersExhibitonThumbAdd=new UsersExhibitonThumbs();
//            usersExhibitonThumbAdd.setUsersId(userIdDto.getUsersId());
//            usersExhibitonThumbAdd.setExhibitonId(userIdDto.getExhibitionId());
//            usersExhibitonThumbAdd.setExhibitionUsersId(userIdDto.getExhibitionId());
//            usersExhibitonThumbsMapper.insert(usersExhibitonThumbAdd);
//            return  thumbs+1;
//        }else{
//            //减少点赞次数
//            exhibitionMapper.thumbsByIdUpdate(userIdDto.getExhibitionId());
//            //删除关联数据
//            usersExhibitonThumbsMapper.deleteById(usersExhibitonThumbs.getId());
//            return  thumbs-1;
//        }
//        mainCommentMapper.insert();
//    }
    }
}
