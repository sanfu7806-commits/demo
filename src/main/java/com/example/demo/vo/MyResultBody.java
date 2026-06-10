package com.example.demo.vo;


import com.example.demo.enmu.GlobalEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 返回统一的返回体
 *
 * */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResultBody<T> implements Serializable {
    private static final long serialVersionUID = -5691489694164932897L;

    private Integer code;

    private String message;

    private T data;

    public static<T> MyResultBody<T> success(Integer code,String message,T result){
        return new MyResultBody<>(code,message,result);
    }

    public static<T> MyResultBody<T> success(Integer code,String message){
        return success(code,message,null);
    }

    public static<T> MyResultBody<T> success(T result){
        return success(GlobalEnum.SUCCESS.getCode(),GlobalEnum.SUCCESS.getMessage(),result);
    }

    public static<T> MyResultBody<T> success(){
        return success(GlobalEnum.SUCCESS.getCode(), GlobalEnum.SUCCESS.getMessage(),null);
    }

    public static<T> MyResultBody<T> error(Integer code,String message,T result){
        return new MyResultBody<T>(code,message,result);
    }

    public static<T> MyResultBody<T> error(Integer code,String message){
        return error(code,message,null);
    }

    public static<T> MyResultBody<T> error(T result){
        return error(GlobalEnum.UNKNOWN.getCode(), GlobalEnum.UNKNOWN.getMessage(),null);
    }

    public static<T> MyResultBody<T> error(Integer code){
        GlobalEnum[] globalEnum = GlobalEnum.values();
        String message = null;
        for (GlobalEnum anEnum : globalEnum) {
            if (anEnum.getCode().equals(code)) {
                message = anEnum.getMessage();
            }
        }
        return error(code,message,null);
    }
}