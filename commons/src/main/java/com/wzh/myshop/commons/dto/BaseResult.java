package com.wzh.myshop.commons.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wzh
 * @date 2019/9/23 - 14:48
 */
@Data
@Accessors(chain = true)
public class BaseResult<T> {
    private Integer status;
    private String message;
    private Object data;
    public static BaseResult ok(){
        return createBaseResult(200,"ok");
    }
    public static BaseResult ok(String message){
        return createBaseResult(200,message);
    }
    public static BaseResult okWithData(Object data){
        return createBaseResult(200,"ok",data);
    }
    public static BaseResult ok(Integer status,String message){
        return createBaseResult(status,message);
    }
    public static BaseResult not_ok(){
        return createBaseResult(500,"not_ok");
    }
    public static BaseResult not_ok(String message){
        return createBaseResult(500,message);
    }
    public static BaseResult not_ok(Integer status,String message){
        return createBaseResult(status,message);
    }

    private static BaseResult createBaseResult(Integer status,String message){
        return createBaseResult(status,message,null);
    }
    private static BaseResult createBaseResult(Integer status,String message,Object data){
        return new BaseResult().setStatus(status).setMessage(message).setData(data);
    }
}
