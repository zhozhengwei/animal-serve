package com.zzw.animalserve.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzw.animalserve.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Byterain
 * @since 2022-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("name")
    private String name;

    @TableField("code")
    private String code;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField(exist = false)
    private List<Long> menuIds = new ArrayList<>();
}
