package modules.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.entity.BaseEntity;

/**
 * <p>
 * 第二级评论表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="MinorComment对象", description="第二级评论表")
public class MinorCommentReturnVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "关联一级评论表main_comment")
    private Long mainCommentId;

    @ApiModelProperty(value = "评论")
    private String comment;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @ApiModelProperty(value = "评论人id")
    private Long userId;

    @ApiModelProperty(value = "是否自己0:没有点赞,1:点赞了")
    private Integer type;

    @ApiModelProperty(value = "关联exhibition表小程序作品id")
    private Long exhibitionId;
}
