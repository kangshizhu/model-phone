package modules.service.impl;

import modules.entity.Exhibition;
import modules.mapper.ExhibitionMapper;
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

    //小程序作品查询
    @Override
    public List<ExhibitionnVo> selectAll() {
        List<ExhibitionnVo> exhibitionnVoList=exhibitionMapper.selectAll();
        return exhibitionnVoList;
    }

    //查询单个小程序作用
    @Override
    public List<ExhibitionReturnVo> selectById(Long id) {
        List<ExhibitionReturnVo> list=exhibitionMapper.selectByIdReturn(id);
        return list;
    }


}
