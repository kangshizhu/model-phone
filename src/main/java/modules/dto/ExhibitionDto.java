package modules.dto;

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
@ApiModel(value="Exhibition对象", description="作品展示")
public class ExhibitionDto extends BaseEntity {

    private static final long serialVersionUID = 1L;


    //@NotNull(message = "导航栏navigation关联的id不能为空")
    @ApiModelProperty(value = "导航栏navigation关联的id",required = true)
    private Long navigationId;

    @NotNull(message = "导航栏navigation关联的id不能为空")
    @ApiModelProperty(value = "发布作品人id 关联user的id",required = true)
    private Long usersId;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;


    @ApiModelProperty(value = "作品路径list",required = true)
    private String exhibitionUrl;

    @ApiModelProperty(value = "关注数量")
    private Integer follows;


    @NotBlank(message = "介绍不能为空")
    @ApiModelProperty(value = "介绍",required = true)
    private String introduce;


}
