package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.UserIdDto;
import modules.entity.Exhibition;
import modules.entity.UsersExhibitionFollows;
import modules.entity.UsersExhibitonThumbs;
import modules.mapper.ExhibitionMapper;
import modules.mapper.UsersExhibitionFollowsMapper;
import modules.mapper.UsersExhibitonThumbsMapper;
import modules.service.IExhibitionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.vo.ExhibitionReturnVo;
import modules.vo.ExhibitionnVo;
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

    //小程序作品查询
    @Override
    public List<ExhibitionnVo> selectAll(Long userId) {
        List<ExhibitionnVo> exhibitionnVoList=exhibitionMapper.selectAll(userId);
        return exhibitionnVoList;
    }


    //查询单个小程序所有
    @Override
    public List<ExhibitionReturnVo> selectById(Long id) {
        List<ExhibitionReturnVo> list=exhibitionMapper.selectByIdReturn(id);
        return list;
    }

    //小程序作品点赞根据作品id
    @Override
    public Integer  thumbsById(UserIdDto userIdDto) {
        //查询是否点赞过
        QueryWrapper<UsersExhibitonThumbs> usersExhibitonThumbsQueryWrapper=new QueryWrapper<>();
        usersExhibitonThumbsQueryWrapper.eq("users_id",userIdDto.getUserId()).eq("exhibiton_id",userIdDto.getExhibitionId());
        UsersExhibitonThumbs usersExhibitonThumbs=usersExhibitonThumbsMapper.selectOne(usersExhibitonThumbsQueryWrapper);

        //获取多少点赞次数并返回
        QueryWrapper<Exhibition> ExhibitionQueryWrapper=new QueryWrapper<>();
        ExhibitionQueryWrapper.eq("id",userIdDto.getExhibitionId());
        Integer thumbs=exhibitionMapper.selectOne(ExhibitionQueryWrapper).getThumbs();
        //没点赞则添加数据 点赞则取消点赞
        if(usersExhibitonThumbs==null){

            //添加点赞次数
            exhibitionMapper.thumbsById(userIdDto.getExhibitionId());
            //添加关联数据
            UsersExhibitonThumbs usersExhibitonThumbAdd=new UsersExhibitonThumbs();
            usersExhibitonThumbAdd.setUsersId(userIdDto.getUserId());
            usersExhibitonThumbAdd.setExhibitonId(userIdDto.getExhibitionId());
            usersExhibitonThumbsMapper.insert(usersExhibitonThumbAdd);
            return  thumbs+1;
        }else{
            //减少点赞次数
            exhibitionMapper.thumbsByIdUpdate(userIdDto.getExhibitionId());
            //删除关联数据
            usersExhibitonThumbsMapper.deleteById(usersExhibitonThumbs.getId());
            return  thumbs-1;
        }

    }

    ////小程序作品收藏取消收藏根据作品id
    @Override
    public Integer followsById(UserIdDto userIdDto) {
        //查询是否点收藏
        QueryWrapper<UsersExhibitionFollows> usersUsersExhibitionFollowsWrapper=new QueryWrapper<>();
        usersUsersExhibitionFollowsWrapper.eq("users_id",userIdDto.getUserId()).eq("exhibiton_id",userIdDto.getExhibitionId());
        UsersExhibitionFollows usersExhibitionFollows=usersExhibitionFollowsMapper.selectOne(usersUsersExhibitionFollowsWrapper);

        //获取多少收藏次数并返回
        QueryWrapper<Exhibition> ExhibitionQueryWrapper=new QueryWrapper<>();
        ExhibitionQueryWrapper.eq("id",userIdDto.getExhibitionId());
        Integer follows=exhibitionMapper.selectOne(ExhibitionQueryWrapper).getFollows();
        //没收藏则添加数据 收藏则取消收藏
        System.out.println(usersExhibitionFollows);
        if(usersExhibitionFollows==null){
            //添加收藏次数
            exhibitionMapper.followsById(userIdDto.getExhibitionId());
            //添加收藏数据
            UsersExhibitionFollows usersExhibitionFollowsAdd=new UsersExhibitionFollows();
            usersExhibitionFollowsAdd.setUsersId(userIdDto.getUserId());
            usersExhibitionFollowsAdd.setExhibitonId(userIdDto.getExhibitionId());
            usersExhibitionFollowsMapper.insert(usersExhibitionFollowsAdd);
            return  follows+1;
        }else{
            //减少收藏次数
            exhibitionMapper.followsByIdUpdate(userIdDto.getExhibitionId());
            //删除收藏数据
            usersExhibitionFollowsMapper.deleteById(usersExhibitionFollows.getId());
            return  follows-1;
        }
    }


}
