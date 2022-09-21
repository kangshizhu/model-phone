package modules.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.entity.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 第一级评论表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="MainComment对象", description="第一级评论表")
public class MainCommentDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "评论不能为空")
    @ApiModelProperty(value = "评论",required = true)
    private String comment;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @NotNull(message = "评论人不能为空")
    @ApiModelProperty(value = "评论人",required = true)
    private Long usersId;

    @NotNull(message = "关联exhibition表小程序作品id不能为空")
    @ApiModelProperty(value = "关联exhibition表小程序作品id",required = true)
    private Long exhibitionId;
}
