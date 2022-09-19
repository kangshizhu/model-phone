package modules.mapper;

import modules.entity.Exhibition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import modules.vo.ExhibitionnVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-17
 */
public interface ExhibitionMapper extends BaseMapper<Exhibition> {

    List<ExhibitionnVo> selectAll();
}
