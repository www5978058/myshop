package com.wzh.myshop.domain.entity;

import com.wzh.myshop.commons.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class ContentCategory extends BaseEntity{

    private Integer parentId;

    private String name;
    private Integer status;

    private Integer sortOrder;

    private Integer isParent;
    private ContentCategory parent;
}
