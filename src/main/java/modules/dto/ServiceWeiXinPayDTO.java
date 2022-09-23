package modules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ServiceWeiXinPayDTO {


    /**用户id*/
    @NotBlank(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id",required = true)
    private String  userId;

    /**用户名称*/
    @NotBlank(message = "用户名称不能为空")
    @ApiModelProperty(value = "用户名称",required = true)
    private String  userName;

    /**金额*/
    @NotBlank(message = "金额不能为空")
    @ApiModelProperty(value = "金额",required = true)
    private String  totalFee;

    /**商品描述*/
    @NotBlank(message = "商品描述不能为空")
    @ApiModelProperty(value = "商品描述",required = true)
    private String  description;

    /**购买次数*/
    @NotNull(message = "商品描述不能为空")
    @ApiModelProperty(value = "购买次数",required = true)
    private Integer  numbers;

    /**接口地址*/
    @NotBlank(message = "接口地址")
    @ApiModelProperty(value = "接口地址",required = true)
    private String  apiUrl;


    /**支付是否成功（0，未支付，1支付）*/
    @ApiModelProperty(value = "description",required = true)
    private Integer  type;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Date time;

}
