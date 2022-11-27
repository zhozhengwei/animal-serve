package com.zzw.animalserve.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/10__2:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    /**
     * 发送邮箱列表
     */
    @NotEmpty
    private List<String> tos;

    /**
     * 主题
     */
    @NotBlank
    private String subject;

    /**
     * 内容
     */
    @NotBlank
    private String content;
}
