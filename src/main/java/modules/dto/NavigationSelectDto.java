package modules.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.entity.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Navigation对象", description="导航栏")
@TableName("navigation")
public class NavigationSelectDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @NotBlank(message = "导航栏名称不能为空")
    @ApiModelProperty(value = "导航栏名称",required = true)
    private String labelName;

    @NotNull(message = "排序不能为空")
    @ApiModelProperty(value = "排序",required = true)
    private int sort;

    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码",required = true)
    private int pageNo;

    @NotNull(message = "条数不能为空")
    @ApiModelProperty(value = "条数",required = true)
    private int pageSize;


}
