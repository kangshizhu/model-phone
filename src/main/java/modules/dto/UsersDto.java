package modules.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.entity.BaseEntity;
import modules.util.phone.Phone;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
@ApiModel(value="Users对象", description="")
public class UsersDto extends BaseEntity {

    private static final long serialVersionUID = 1L;



    @ApiModelProperty(value = "账号")
    private String username;


    @ApiModelProperty(value = "密码")
    private String password;

    @Phone
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "用户类型(0:后台管理员,1:普通用户,2:达人,3:机构,4:品牌商家)")
    private Integer type;


}
