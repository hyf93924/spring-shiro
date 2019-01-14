/**
 * 6006.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.yif.tools;

/**
 * json返回类
 *
 * @author heyif
 * @since v1.0 2018-10-30T19:29
 */
public class JsonResult {
    private String status = null;
    private Object result = null;

    public JsonResult status(String status){
        this.status = status;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
