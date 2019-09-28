package com.wzh.myshop.commons.dto;

import com.wzh.myshop.commons.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author wzh
 * @date 2019/9/25 - 12:59
 */
@Data
@Accessors(chain = true)
public class PageInfo<T extends BaseEntity> implements Serializable {
    private static final long serialVersionUID = -8319941281768481797L;
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<T> data;
    private String error;
}
