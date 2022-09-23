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
 * 评论点赞与人关联表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="UsersExhibitonThumbs对象", description="作品点赞与人关联表")
public class UsersExhibitonThumbs extends BaseEntity {

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

    @ApiModelProperty(value = "作品创作人关联exhibition的users_id")
    private Long exhibitionUsersId;


}
