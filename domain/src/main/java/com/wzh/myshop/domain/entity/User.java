package com.wzh.myshop.domain.entity;

import com.wzh.myshop.commons.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wzh.myshop.commons.utils.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {
    @Length(min = 6,max = 20,message = "用户名长度必须介于6 - 20位之间")
    private String username;
    @JsonIgnore
    private String password;
    @Pattern(regexp = RegexpUtils.PHONE,message = "手机号码格式不正确")
    private String mobileNumber;
    @Pattern(regexp = RegexpUtils.EMAIL,message = "邮箱格式不正确")
    private String email;

}
