package modules.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class IdDto {

    @NotNull(message = "主键id不能为null")
    @ApiModelProperty(value = "主键id",required = true)
    private Long id;
}
