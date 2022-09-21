package modules.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 第一级评论表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@Data
@Accessors(chain = true)
@ApiModel(value="MainComment对象", description="第一级评论表")
public class MainCommentReturnVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论")
    private String comment;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @ApiModelProperty(value = "评论人")
    private Long usersId;

    @ApiModelProperty(value = "关联exhibition表小程序作品id")
    private Long exhibitionId;

    @ApiModelProperty(value = "二级评论Vo")
    private List<MinorCommentReturnVo> minorCommentReturnVoList;
}
