package modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import modules.entity.BaseEntity;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.util.phone.Phone;

/**
 * <p>
 * 
 * </p>
 *
 * @author astupidcoder
 * @since 2022-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Users对象", description="用户表")
@TableName("users")
public class Users extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户类型(0:后台管理员,1:普通用户,2:达人,3:机构,4:品牌商家)")
    private Integer type;


}
