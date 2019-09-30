package com.wzh.myshop.web.admin.service.impl;

import com.wzh.myshop.commons.base.BaseServiceImpl;
import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.commons.dto.PageInfo;
import com.wzh.myshop.commons.validator.BeanValidator;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.ContentExample;
import com.wzh.myshop.web.admin.mapper.ContentMapper;
import com.wzh.myshop.web.admin.service.ContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wzh
 * @date 2019/9/27 - 11:03
 */
@Service
public class ContentServiceImpl extends BaseServiceImpl<Content,ContentExample,ContentMapper> implements ContentService {

}
