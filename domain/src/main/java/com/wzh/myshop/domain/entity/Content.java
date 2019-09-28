package com.wzh.myshop.domain.entity;

import com.wzh.myshop.commons.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class Content extends BaseEntity {
    private Integer id;
    @NotNull(message = "父级目录不能为空")
    private Integer categoryId;
    @Length(min = 1,max = 20,message = "标题长度介于1 - 20之间")
    private String title;
    @Length(min = 1,max = 20,message = "子标题长度介于1 - 20之间")
    private String subTitle;
    @Length(min = 1,max = 50,message = "标题描述长度介于1 - 50之间")
    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private LocalDateTime ctime;

    private LocalDateTime mtime;
    @Length(min = 1,message = "内容不可为空")
    private String content;

}
