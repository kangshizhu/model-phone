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
public class MinorCommentDto extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "关联一级评论表main_comment id不能为空")
    @ApiModelProperty(value = "关联一级评论表main_comment",required = true)
    private Long mainCommentId;

    @NotBlank(message = "评论不能为空")
    @ApiModelProperty(value = "评论",required = true)
    private String comment;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @NotNull(message = "评论人不能为空")
    @ApiModelProperty(value = "评论人id",required = true)
    private Long userId;


}
