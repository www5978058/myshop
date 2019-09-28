package com.wzh.myshop.commons.base;

import lombok.Data;

/**
 * @author wzh
 * @date 2019/9/25 - 13:06
 */
@Data
public abstract class BaseExample {
    protected Integer limitStart;
    protected Integer limitEnd;
}
