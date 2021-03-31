package com.example.jecktao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 工具表
 * </p>
 *
 * @author leitao
 * @since 2021-01-16
 */
@Data
@ApiModel("工具实体类")
public class Tools {

    private static final long serialVersionUID = 1L;

    /**
     * 工具名称
     */
    @ApiModelProperty("工具名称")
    private String name;

    /**
     * 工具说明
     */
    @ApiModelProperty("工具说明")
    private String description;

    /**
     * 工具链接地址
     */
    @ApiModelProperty("工具链接地址")
    private String url;

    @ApiModelProperty("图标路径")
    private String icon;


}
