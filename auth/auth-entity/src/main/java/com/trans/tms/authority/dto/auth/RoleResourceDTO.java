package com.trans.tms.authority.dto.auth;

import com.trans.tms.authority.entity.auth.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleResourceDTO", description = "角色")
public class RoleResourceDTO {
    private Long id;
    @ApiModelProperty("角色名称")
    @Length(
            max = 30,
            message = "角色名称长度不能超过30"
    )
    private String name;
    @ApiModelProperty("角色编码")
    private String code;
    private List<Resource> resources;
}
