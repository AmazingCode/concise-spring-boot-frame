package com.unreview.utils.http;


import com.fasterxml.jackson.core.type.TypeReference;
import com.unreview.model.bo.enums.HttpMethod;
import java.io.IOException;
import java.util.HashMap;

/**
 * Http统一帮助类
 * @param <T>
 * @param <B>
 */
public class HttpHelper<T,B> {

    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求类型
     */
    private HttpMethod method;
    /**
     * 请求headers
     */
    private HashMap<String,String> headers;
    /**
     * 请求body
     */
    private B body;


    //private T tinstance;

    public static   <T,B>  HttpHelper<T,B> builder(TypeReference<T> t, TypeReference<B> b) throws IllegalAccessException, InstantiationException {
        HttpHelper result= new HttpHelper<T,B>();
        result.headers=new HashMap();
        //result.tinstance= t.getType().getClass().newInstance();
        return result;
    }
    public HttpHelper<T,B> url(String url)
    {
        this.url=url;
        return this;
    }
    public HttpHelper<T,B> method(HttpMethod method)
    {
        this.method=method;
        return this;
    }
    public HttpHelper<T,B> header(String key,String value)
    {
        this.headers.put(key,value);
        return this;
    }
    public HttpHelper<T,B> body(B body)
    {
        this.body=body;
        return this;
    }
    public T  execute() throws IOException {

      return  new HttpUnit<T,B>().
                url(url).
                method(method).
                headers(headers).
                body(body).
                //tInstance(tinstance).
                execute();

    }
}
