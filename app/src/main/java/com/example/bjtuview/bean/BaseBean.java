/**
 * Copyright 2023 bejson.com
 */
package com.example.bjtuview.bean;
import androidx.annotation.NonNull;

import java.util.List;

/**
 * Auto-generated: 2023-05-18 16:1:22
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
/**
 * 这里的Bean是用来接受服务器传过来的项目用的。由http://www.bejson.com/java2pojo/这个网站生成的代码。
 * 这里写成了一个泛型类，实际上只用到了Goods（就实际代码里面只有Goods一种，所以这泛型只用在了Goods上。之后如果要添加别的数据类型也可以直接按照Goods的用法使用BaseBean)
 */
public class BaseBean<T> {

    private int code;
    private String message;
    private T data;
    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(T data) {
        this.data = data;
    }
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}