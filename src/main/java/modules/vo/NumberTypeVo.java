package modules.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description:
 * @author:kangshizhu
 * @create:2022/9/23-16:16
 **/
@Data
public class NumberTypeVo {

    @ApiModelProperty(value = "条数")
    private Integer numbers;

    @ApiModelProperty(value = "0:没有点赞,1:点赞")
    private Integer type;
}
