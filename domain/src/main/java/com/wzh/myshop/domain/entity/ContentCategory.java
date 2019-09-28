package com.wzh.myshop.domain.entity;

import com.wzh.myshop.commons.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class ContentCategory extends BaseEntity{
    private Integer id;

    private Integer parentId;

    private String name;
    private Integer status;

    private Integer sortOrder;

    private Integer isParent;

    private LocalDateTime ctime;

    private LocalDateTime mtime;

}
