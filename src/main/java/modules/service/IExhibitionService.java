package modules.service;

import modules.entity.Exhibition;
import com.baomidou.mybatisplus.extension.service.IService;
import modules.vo.ExhibitionnVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
public interface IExhibitionService extends IService<Exhibition> {


    //小程序作品查询
    List<ExhibitionnVo> selectAll();
}
