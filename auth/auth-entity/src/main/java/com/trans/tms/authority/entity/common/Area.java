package com.trans.tms.authority.entity.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trans.tms.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@TableName("area")
@ApiModel(
        value = "Area",
        description = "行政区域"
)
@Data
public class Area extends Entity<Long> {
    @ApiModelProperty("父级区域id")
    @TableField("parent_id")
    private Long parentId;
    @ApiModelProperty("行政区域名称")
    @Length(
            max = 255,
            message = "行政区域名称长度不能超过255"
    )
    @TableField(
            value = "name",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String name;
    @ApiModelProperty("行政区域编码")
    @Length(
            max = 255,
            message = "行政区域编码长度不能超过255"
    )
    @TableField(
            value = "area_code",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String areaCode;
    @ApiModelProperty("城市编码")
    @Length(
            max = 255,
            message = "城市编码长度不能超过255"
    )
    @TableField(
            value = "city_code",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String cityCode;
    @ApiModelProperty("合并名称")
    @Length(
            max = 255,
            message = "合并名称长度不能超过255"
    )
    @TableField(
            value = "merger_name",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String mergerName;
    @ApiModelProperty("简称")
    @Length(
            max = 255,
            message = "简称长度不能超过255"
    )
    @TableField(
            value = "short_name",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String shortName;
    @ApiModelProperty("邮政编码")
    @Length(
            max = 255,
            message = "邮政编码长度不能超过255"
    )
    @TableField(
            value = "zip_code",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String zipCode;
    @ApiModelProperty("行政区域等级（0: 省级 1:市级 2:县级 3:镇级 4:乡村级）")
    @TableField("level")
    private Integer level;
    @ApiModelProperty("经度")
    @Length(
            max = 255,
            message = "经度长度不能超过255"
    )
    @TableField(
            value = "lng",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String lng;
    @ApiModelProperty("纬度")
    @Length(
            max = 255,
            message = "纬度长度不能超过255"
    )
    @TableField(
            value = "lat",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String lat;
    @ApiModelProperty("拼音")
    @Length(
            max = 255,
            message = "拼音长度不能超过255"
    )
    @TableField(
            value = "pinyin",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String pinyin;
    @ApiModelProperty("首字母")
    @Length(
            max = 255,
            message = "首字母长度不能超过255"
    )
    @TableField(
            value = "first",
            condition = "%s LIKE CONCAT('%%',#{%s},'%%')"
    )
    private String first;

}