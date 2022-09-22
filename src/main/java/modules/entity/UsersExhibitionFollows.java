package modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import modules.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @author kangshizhu
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UsersExhibitionFollows对象", description="作品收藏与人关联表")
public class UsersExhibitionFollows extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;


    @ApiModelProperty(value = "点赞人id关联users的id")
    private Long usersId;

    @ApiModelProperty(value = "作品id关联exhibition的id")
    private Long exhibitonId;


}
