package com.wzh.myshop.web.api.controller.v1;

import com.wzh.myshop.commons.dto.BaseResult;
import com.wzh.myshop.domain.entity.Content;
import com.wzh.myshop.domain.entity.ContentExample;
import com.wzh.myshop.service.ContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzh
 * @date 2019/10/10 - 10:07
 */
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class ContentController {
    @Autowired
    private ContentService contentService;
    /**
     * 幻灯片接口
     * @return
     */
    @GetMapping("ppt")
    public BaseResult findPPT() {
        ContentExample e = new ContentExample();
        e.createCriteria().andCategoryIdEqualTo(89);
        List<Content> contents = contentService.selectByExample(e);
        return BaseResult.okWithData(contents);
    }
}
