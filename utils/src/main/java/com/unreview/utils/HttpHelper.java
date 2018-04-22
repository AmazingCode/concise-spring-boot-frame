package com.unreview.utils;

import com.unreview.model.bo.enums.HttpMethod;

import java.util.HashMap;

public class HttpHelper {
    private HttpHelper(){}
    private String url;
    private HttpMethod method;
    private HashMap<String,String> headers;
    public HttpHelper builder()
    {
        return new HttpHelper();
    }
    public HttpHelper url(String url)
    {
        this.url=url;
        return this;
    }
    public HttpHelper method(HttpMethod method)
    {
        this.method=method;
        return this;
    }
    public HttpHelper header(String key,String value)
    {
        headers.put(key,value);
        return this;
    }
    public Object execute()
    {
        return null;
    }

}
