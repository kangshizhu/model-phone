package modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import modules.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 二级评论点赞人和二级评论关联表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="MinorCommentThumbs对象", description="二级评论点赞人和二级评论关联表")
public class MinorCommentThumbs extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "二级评论id")
    private Long minorCommentId;

    @ApiModelProperty(value = "二级评论点赞人id")
    private Long minorCommentUsersThumbsId;

    @ApiModelProperty(value = "关联exhibition表小程序作品id")
    private Long exhibitionId;


}
