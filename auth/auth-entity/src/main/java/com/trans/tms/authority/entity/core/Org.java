package com.trans.tms.authority.entity.core;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trans.tms.base.entity.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 组织
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("core_org")
@ApiModel(value = "Org", description = "组织")
public class Org extends Entity<Long> {

    @ApiModelProperty("名称")
    @NotEmpty(
            message = "名称不能为空"
    )
    @Length(
            max = 255,
            message = "名称长度不能超过255"
    )
    @TableField(
            value = "name",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String name;
    @ApiModelProperty("简称")
    @Length(
            max = 255,
            message = "简称长度不能超过255"
    )
    @TableField(
            value = "abbreviation",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String abbreviation;
    @ApiModelProperty("父级ID")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("父级名称")
    @TableField(
            exist = false
    )
    private String parentName;
    @ApiModelProperty("部门类型 1为分公司，2为一级转运中心 3为二级转运中心 4为网点")
    @TableField("org_type")
    private Integer orgType;
    @ApiModelProperty("所属省份id")
    @TableField("province_id")
    private Long provinceId;
    @ApiModelProperty("所属省份名称")
    @TableField(
            exist = false
    )
    private String provinceName;
    @ApiModelProperty("所属城市id")
    @TableField("city_id")
    private Long cityId;
    @ApiModelProperty("所属城市名称")
    @TableField(
            exist = false
    )
    private String cityName;
    @ApiModelProperty("所属区县id")
    @TableField("county_id")
    private Long countyId;
    @ApiModelProperty("所属区县名称")
    @TableField(
            exist = false
    )
    private String countyName;
    @ApiModelProperty("详细地址")
    @Length(
            max = 255,
            message = "详细地址长度不能超过255"
    )
    @TableField(
            value = "address",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String address;
    @ApiModelProperty("经度")
    @Length(
            max = 255,
            message = "经度长度不能超过255"
    )
    @TableField(
            value = "longitude",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String longitude;
    @ApiModelProperty("纬度")
    @Length(
            max = 255,
            message = "纬度长度不能超过255"
    )
    @TableField(
            value = "latitude",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String latitude;
    @ApiModelProperty("联系电话")
    @Length(
            max = 255,
            message = "联系电话长度不能超过255"
    )
    @TableField(
            value = "contract_number",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String contractNumber;
    @ApiModelProperty("负责人id")
    @TableField("manager_id")
    private Long managerId;
    @ApiModelProperty("负责人名称")
    @TableField(
            exist = false
    )
    private String manager;
    @ApiModelProperty("营业时间")
    @Length(
            max = 255,
            message = "营业时间长度不能超过255"
    )
    @TableField(
            value = "business_hours",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String businessHours;
    @ApiModelProperty("树结构")
    @Length(
            max = 255,
            message = "树结构长度不能超过255"
    )
    @TableField(
            value = "tree_path",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String treePath;
    @ApiModelProperty("排序")
    @TableField("sort_value")
    private Integer sortValue;
    @ApiModelProperty("状态")
    @TableField("status")
    private Boolean status;
    @ApiModelProperty("描述")
    @Length(
            max = 255,
            message = "描述长度不能超过255"
    )
    @TableField(
            value = "describe_",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String describe;

}
