package modules.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author:kangshizhu
 * @create:2022/9/22-10:05
 **/
@Data
public class MinorCommentThumbsDto {

    @NotNull(message = "二级评论id不能为null")
    @ApiModelProperty(value = "一级评论id",required = true)
    private Long minorCommentId;

    @NotNull(message = "usersId不能为null")
    @ApiModelProperty(value = "usersId",required = true)
    private Long usersId;

    @NotNull(message = "关联exhibition表小程序作品id不能为空")
    @ApiModelProperty(value = "关联exhibition表小程序作品id",required = true)
    private Long exhibitionId;
}
