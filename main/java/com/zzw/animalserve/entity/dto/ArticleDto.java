package com.zzw.animalserve.entity.dto;

import com.zzw.animalserve.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description(描述)
 * @autor: zhouzhengwei
 * @date: 2022/10/18__14:09
 */
@ApiModel(value = "文章DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    @ApiModelProperty(value= "ID")
    private Long id;

    @ApiModelProperty(value= "主题")
    private String title;

    @ApiModelProperty(value= "简单介绍")
    private String introduction;

    @ApiModelProperty(value= "用户")
    private Long uid;

    @ApiModelProperty(value= "封面")
    private String cover;

    @ApiModelProperty(value= "文本内容")
    private String content;

    @ApiModelProperty(value= "类型")
    private Integer type;

    @ApiModelProperty(value= "标签列表")
    private List<String> tagName;

    public Article toEntity() {
        Article article = new Article();
        article.setArticleId(id);
        article.setArticleTitle(title);
        article.setArticleIntroduction(introduction);
        article.setMemberId(uid);
        article.setArticleCover(cover);
        article.setArticleText(content);
        article.setArticleAttribute(type);
        return article;
    }

}
