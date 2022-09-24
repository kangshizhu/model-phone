package modules.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author:kangshizhu
 * @create:2022/9/22-10:05
 **/
@Data
public class MinorCommentDto {

    @ApiModelProperty(value = "关联一级评论表main_comment",required = true)
    private Long mainCommentId;

    @NotBlank(message = "评论不能为空")
    @ApiModelProperty(value = "评论",required=true)
    private String comment;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @ApiModelProperty(value = "评论人id",required=true)
    private Long usersId;

    @NotNull(message = "关联exhibition表小程序作品id不能为空")
    @ApiModelProperty(value = "关联exhibition表小程序作品id",required = true)
    private Long exhibitionId;
}
