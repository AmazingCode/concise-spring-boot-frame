package com.unreview.utils.http;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Http Get请求逻辑
 * @param <T>
 * @param <B>
 */
public class MyHttpGet<T,B> implements MyHttp<T,B> {

    @Override
    public T execute(String url, HashMap<String, String> headers, B body, CloseableHttpClient client) throws IOException {

        //新建HttpGet对象
        HttpGet httpGet=new HttpGet(url);
        //设置header
        for (Map.Entry<String,String> en:headers.entrySet())
        {
            httpGet.setHeader(en.getKey(),en.getValue());
        }
        //设置超时时间和连接时间
        RequestConfig selfConfig=RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(10000).build();

        httpGet.setConfig(selfConfig);

        String resStr= EntityUtils.toString(client.execute(httpGet).getEntity(),"UTF-8");
        //执行逻辑并resStr且Json成对象
//        return JSON.parseObject(resStr,
//                ,new TypeReference<T>(){});


//        Object o= JSON.parseObject(resStr,tinstance.getClass());
//



        //bug  T获取不到 无法强转
        return (T)JSON.parseObject(resStr,new TypeReference<Object>(){});
    }
}
