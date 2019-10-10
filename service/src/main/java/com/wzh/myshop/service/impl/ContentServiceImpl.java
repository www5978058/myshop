package com.wzh.myshop.service.impl;

import com.wzh.myshop.commons.base.BaseServiceImpl;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.ContentExample;
import com.wzh.myshop.mapper.ContentMapper;
import com.wzh.myshop.service.ContentService;
import org.springframework.stereotype.Service;

/**
 * @author wzh
 * @date 2019/9/27 - 11:03
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content,ContentExample, ContentMapper> implements ContentService {

}
