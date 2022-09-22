package modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import modules.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Exhibition对象", description="")
public class Exhibition extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "导航栏名称")
    private String labelName;

    @ApiModelProperty(value = "导航栏navigation关联的id",required = true)
    private Long navigationId;

    @ApiModelProperty(value = "发布作品人id 关联user的id")
    private Long usersId;

    @ApiModelProperty(value = "点赞数量")
    private Integer thumbs;

    @ApiModelProperty(value = "作品路径list")
    private String exhibitionUrl;

    @ApiModelProperty(value = "收藏数量")
    private Integer follows;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "评论数量")
    private Integer commentNumbers;

    @ApiModelProperty(value = "审核是否通过(0:未通过，1:通过)")
    private Integer examineType;

}
