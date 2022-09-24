package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.ExhibitionIdDto;
import modules.dto.UserIdDto;
import modules.entity.Exhibition;
import modules.entity.MainCommentThumbs;
import modules.entity.UsersExhibitionFollows;
import modules.entity.UsersExhibitonThumbs;
import modules.mapper.ExhibitionMapper;
import modules.mapper.MainCommentThumbsMapper;
import modules.mapper.UsersExhibitionFollowsMapper;
import modules.mapper.UsersExhibitonThumbsMapper;
import modules.service.IExhibitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.vo.ExhibitionReturnVo;
import modules.vo.ExhibitionnVo;
import modules.vo.NumberTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
@Service
public class ExhibitionServiceImpl extends ServiceImpl<ExhibitionMapper, Exhibition> implements IExhibitionService {
    @Resource
    ExhibitionMapper exhibitionMapper;
    @Resource
    UsersExhibitonThumbsMapper usersExhibitonThumbsMapper;
    @Resource
    UsersExhibitionFollowsMapper usersExhibitionFollowsMapper;
    @Resource
    MainCommentThumbsMapper mainCommentThumbsMapper;

    //小程序作品查询
    @Override
    public List<ExhibitionnVo> selectAll(Long userId) {
        List<ExhibitionnVo> exhibitionnVoList=exhibitionMapper.selectAll(userId);
        return exhibitionnVoList;
    }


    //查询单个作品小程序所有
    public ExhibitionReturnVo selectById(ExhibitionIdDto exhibitionIdDto) {
        ExhibitionReturnVo list=exhibitionMapper.selectByIdReturn(exhibitionIdDto.getExhibitionId(),exhibitionIdDto.getUsersId(),exhibitionIdDto.getUsersId());
//        QueryWrapper<MainCommentThumbs> mainCommentThumbsQueryWrapper=new QueryWrapper();
//        mainCommentThumbsQueryWrapper.eq("exhibition_id",exhibitionIdDto.getExhibitionId()).eq("main_comment_users_thumbs_id",exhibitionIdDto.getUsersId());
//        List<MainCommentThumbs> mainCommentThumbsList=mainCommentThumbsMapper.selectList(mainCommentThumbsQueryWrapper);
//        //判断是否有评论数据
//        if(null!=list){
//            //判断一级评论是否有usersId点赞的数据 更改type
//            for (int i = 0; i < list.getMainCommentReturnVoList().size(); i++) {
//                 if(null!=mainCommentThumbsList){
//                     for (int j = 0; j < mainCommentThumbsList.size(); j++) {
//                         //如果一级评论表的id和传入参数usersId都登录main_comment_thumbs里的一级评论id和一级评论点赞人id则该type为1否则为0
//                         if(list.getMainCommentReturnVoList().get(i).getId()==mainCommentThumbsList.get(j).getMainCommentId()
//                         &&mainCommentThumbsList.get(j).getMainCommentUsersThumbsId()==exhibitionIdDto.getUsersId()){
//                             list.getMainCommentReturnVoList().get(i).setType(1);
//                         }else{
//                             list.getMainCommentReturnVoList().get(i).setType(0);
//                         }
//                     }
//                 }
//            }
//        }
        return list;
    }

    //小程序作品点赞根据作品id
    @Override
    public NumberTypeVo thumbsById(UserIdDto userIdDto) {
        //查询是否点赞过
        QueryWrapper<UsersExhibitonThumbs> usersExhibitonThumbsQueryWrapper=new QueryWrapper<>();
        usersExhibitonThumbsQueryWrapper.eq("users_id",userIdDto.getUsersId()).eq("exhibiton_id",userIdDto.getExhibitionId());
        UsersExhibitonThumbs usersExhibitonThumbs=usersExhibitonThumbsMapper.selectOne(usersExhibitonThumbsQueryWrapper);
        //获取多少点赞次数并返回
        QueryWrapper<Exhibition> ExhibitionQueryWrapper=new QueryWrapper<>();
        ExhibitionQueryWrapper.eq("id",userIdDto.getExhibitionId());
        Integer thumbs=exhibitionMapper.selectOne(ExhibitionQueryWrapper).getThumbs();
        NumberTypeVo numberTypeVo=new NumberTypeVo();
        //没点赞则添加数据 点赞则取消点赞
        if(usersExhibitonThumbs==null){

            //添加点赞次数
            exhibitionMapper.thumbsById(userIdDto.getExhibitionId());
            //添加关联数据
            UsersExhibitonThumbs usersExhibitonThumbAdd=new UsersExhibitonThumbs();
            usersExhibitonThumbAdd.setUsersId(userIdDto.getUsersId());
            usersExhibitonThumbAdd.setExhibitonId(userIdDto.getExhibitionId());
            usersExhibitonThumbAdd.setExhibitionUsersId(userIdDto.getExhibitionId());
            usersExhibitonThumbsMapper.insert(usersExhibitonThumbAdd);
            numberTypeVo.setNumbers(thumbs+1);
            numberTypeVo.setType(1);
            return  numberTypeVo;
        }else{
            //减少点赞次数
            exhibitionMapper.thumbsByIdUpdate(userIdDto.getExhibitionId());
            //删除关联数据
            usersExhibitonThumbsMapper.deleteById(usersExhibitonThumbs.getId());
            numberTypeVo.setNumbers(thumbs-1);
            numberTypeVo.setType(0);
            return  numberTypeVo;
        }

    }

    ////小程序作品收藏取消收藏根据作品id
    @Override
    public NumberTypeVo followsById(UserIdDto userIdDto) {
        //查询是否点收藏
        QueryWrapper<UsersExhibitionFollows> usersUsersExhibitionFollowsWrapper=new QueryWrapper<>();
        usersUsersExhibitionFollowsWrapper.eq("users_id",userIdDto.getUsersId()).eq("exhibiton_id",userIdDto.getExhibitionId());
        UsersExhibitionFollows usersExhibitionFollows=usersExhibitionFollowsMapper.selectOne(usersUsersExhibitionFollowsWrapper);

        //获取多少收藏次数并返回
        QueryWrapper<Exhibition> ExhibitionQueryWrapper=new QueryWrapper<>();
        ExhibitionQueryWrapper.eq("id",userIdDto.getExhibitionId());
        Integer follows=exhibitionMapper.selectOne(ExhibitionQueryWrapper).getFollows();

        NumberTypeVo numberTypeVo=new NumberTypeVo();
        //没收藏则添加数据 收藏则取消收藏
        if(usersExhibitionFollows==null){
            //添加收藏次数
            exhibitionMapper.followsById(userIdDto.getExhibitionId());
            //添加收藏数据
            UsersExhibitionFollows usersExhibitionFollowsAdd=new UsersExhibitionFollows();
            usersExhibitionFollowsAdd.setUsersId(userIdDto.getUsersId());
            usersExhibitionFollowsAdd.setExhibitonId(userIdDto.getExhibitionId());
            usersExhibitionFollowsAdd.setExhibitionUsersId(userIdDto.getExhibitionId());
            usersExhibitionFollowsMapper.insert(usersExhibitionFollowsAdd);
            numberTypeVo.setNumbers(follows+1);
            return  numberTypeVo;
        }else{
            //减少收藏次数
            exhibitionMapper.followsByIdUpdate(userIdDto.getExhibitionId());
            //删除收藏数据
            usersExhibitionFollowsMapper.deleteById(usersExhibitionFollows.getId());
            numberTypeVo.setNumbers(follows-1);
            return  numberTypeVo;
        }
    }


}
