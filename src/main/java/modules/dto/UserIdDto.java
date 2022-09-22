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
public class UserIdDto {

    @NotNull(message = "作品id不能为null")
    @ApiModelProperty(value = "作品id",required = true)
    private Long exhibitionId;

    @NotNull(message = "userId不能为null")
    @ApiModelProperty(value = "userId",required = true)
    private Long userId;
}
