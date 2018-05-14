package com.unreview.utils.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.unreview.model.bo.enums.HttpMethod;
import lombok.Data;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.HashMap;

public class HttpUnit<T,B> {
    //private HttpHelper(){}

    //private T tInstance;

    private String url;
    private HttpMethod method;
    private HashMap<String,String> headers;
    private B body;
    //HttpClient连接池
    private static PoolingHttpClientConnectionManager pcm=null;
    //全局统一配置
    private static RequestConfig globalConfig=null;
    static {
        pcm=new PoolingHttpClientConnectionManager();
        pcm.setMaxTotal(100);
        pcm.setDefaultMaxPerRoute(10);
        RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
    }
    private static CloseableHttpClient getHttpClient()
    {
        return HttpClients.custom().setConnectionManager(pcm)
                .setDefaultRequestConfig(globalConfig).build();
    }


    public   HttpUnit<T,B> builder(TypeReference<T> t,TypeReference<B> b)
    {
        HttpUnit<T,B> result=new HttpUnit<>();
        result.headers=new HashMap<>();
        return result;
    }
    public HttpUnit<T,B> url(String url)
    {
        this.url=url;
        return this;
    }
//    public HttpUnit<T,B> tInstance(T t)
//    {
//        this.tInstance=t;
//        return this;
//    }
    public HttpUnit<T,B> body(B body)
    {
        this.body=body;
        return this;
    }
    public HttpUnit<T,B> method(HttpMethod method)
    {
        this.method=method;
        return this;
    }
    public HttpUnit<T,B> headers(HashMap<String,String> headers)
    {
        this.headers=headers;
        return this;
    }
    public T execute() throws IOException {

        MyHttp<T,B> http=null;
        if(method==HttpMethod.GET) {
            http = new MyHttpGet<>();
        }
        return http.execute(url,headers,body,HttpUnit.getHttpClient());

    }

}
