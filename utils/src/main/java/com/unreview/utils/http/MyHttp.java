package com.unreview.utils.http;


import org.apache.http.impl.client.CloseableHttpClient;
import java.io.IOException;
import java.util.HashMap;

/**
 * Http执行接口
 * @param <T>
 * @param <B>
 */
public interface MyHttp<T,B> {
    String url="";
    T execute(String url, HashMap<String,String> headers, B body, CloseableHttpClient client) throws IOException;
}
