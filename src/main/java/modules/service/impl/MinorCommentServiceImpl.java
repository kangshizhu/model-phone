package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import modules.dto.MinorCommentDto;
import modules.dto.MinorCommentThumbsDto;
import modules.entity.MainComment;
import modules.entity.MainCommentThumbs;
import modules.entity.MinorComment;
import modules.entity.MinorCommentThumbs;
import modules.mapper.MainCommentMapper;
import modules.mapper.MainCommentThumbsMapper;
import modules.mapper.MinorCommentMapper;
import modules.mapper.MinorCommentThumbsMapper;
import modules.service.IMinorCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.vo.NumberTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    MinorCommentMapper minorCommentMapper;
    @Resource
    MinorCommentThumbsMapper minorCommentThumbsMapper;

    //二级点赞接口传minor_comment的id
    @Override
    public NumberTypeVo addThumbs(MinorCommentThumbsDto minorCommentThumbsDto) {
        System.out.println(minorCommentThumbsDto.getMinorCommentId()+"xxx"+minorCommentThumbsDto.getUsersId());
        NumberTypeVo numberTypeVo=new NumberTypeVo();
        //查询是否点赞过
        QueryWrapper<MinorCommentThumbs> MinorCommentThumbsQueryWrapper=new QueryWrapper<>();
        MinorCommentThumbsQueryWrapper.eq("minor_comment_id",minorCommentThumbsDto.getMinorCommentId()).eq("minor_comment_users_thumbs_id",minorCommentThumbsDto.getUsersId());
        MinorCommentThumbs minorCommentThumbs=minorCommentThumbsMapper.selectOne(MinorCommentThumbsQueryWrapper);
        //查询
        QueryWrapper<MinorComment> minorCommentQueryWrapper=new QueryWrapper<>();
        minorCommentQueryWrapper.eq("id",minorCommentThumbsDto.getMinorCommentId());
        Integer numbers=minorCommentMapper.selectOne(minorCommentQueryWrapper).getThumbs();
        //没有点赞 开始点赞
        if(minorCommentThumbs==null){
            //添加二级评论次数
            minorCommentMapper.addThumbs(minorCommentThumbsDto.getMinorCommentId());
            //添加二级评论点赞人和一级评论关联表
            MinorCommentThumbs minorCommentThumbsAdd=new MinorCommentThumbs();
            minorCommentThumbsAdd.setMinorCommentId(minorCommentThumbsDto.getMinorCommentId());
            minorCommentThumbsAdd.setMinorCommentUsersThumbsId(minorCommentThumbsDto.getUsersId());
            minorCommentThumbsAdd.setExhibitionId(minorCommentThumbsAdd.getExhibitionId());
            minorCommentThumbsMapper.insert(minorCommentThumbsAdd);
            numberTypeVo.setNumbers(numbers+1);
            numberTypeVo.setType(1);
            //点赞  取消点赞
        }else{
            //减少二级评论次数
            minorCommentMapper.updateThumbs(minorCommentThumbsDto.getMinorCommentId());
            //删除二级评论点赞人和一级评论关联表
            minorCommentThumbsMapper.deleteById(minorCommentThumbs.getId());
            numberTypeVo.setNumbers(numbers-1);
            numberTypeVo.setType(0);
        }

        return numberTypeVo;
    }
}
