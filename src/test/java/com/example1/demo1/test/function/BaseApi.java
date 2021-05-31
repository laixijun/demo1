package com.example1.demo1.test.function;

import org.apache.commons.io.FileUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class BaseApi {
    public static void main(String[] args) {

    }
    // get 无参
    public String doGet() throws Exception {
        {
            //创建HttpClient对象
            CloseableHttpClient httpclient = HttpClients.createDefault();
            //创建http GET请求
            HttpGet httpGet = new HttpGet("http://www.baidu.com");
            CloseableHttpResponse response = null;
            String content = null;
            try {
                //执行请求
                response = httpclient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    //请求体内容
                    content = EntityUtils.toString(response.getEntity(), "UTF-8");
                }
            } finally {
                if (response != null) {
                    response.close();
                }
                //相当于关闭浏览器
                httpclient.close();
                return content;
            }
        }
    }
    // get 有参
    /**
     * 带参数的GET请求
     * 两种方式：
     *       1.直接将参数拼接到url后面 如：?wd=java
     *       2.使用URI的方法设置参数 setParameter("wd", "java")
     */
    public String doGetParam() throws Exception{
        // 创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //定义请求的参数
        URI uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd","java").build();
        //创建http GET请求
        HttpGet httpGet = new HttpGet(uri);
        //response 对象
        CloseableHttpResponse response = null;
        String content = null;
        try{
            // 执行http get 请求
            response = httpclient.execute(httpGet);
            //判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }finally{
            if (response != null){
                response.close();
            }
            httpclient.close();
            return content;
        }

    }
    /**
     * 常规post请求
     *    可以设置Header来伪装浏览器请求
     */
    public String doPost() throws Exception{
        // 创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST
        HttpPost httpPost = new HttpPost("http://www.oschina.net/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = null;
        String content = null;
        try{
            response = httpclient.execute(httpPost);
            //判断返回状态是否为200
            if(response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }finally{
            if(response != null){
                response.close();
            }
            httpclient.close();
            return content;
        }
    }
    /**
     * 带有参数的Post请求
     * NameValuePair
     */
    public String doPostParam() throws Exception{
        //创建httpclient 对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost 请求
        HttpPost httpPost = new HttpPost("http://www.oschina.net/search");
        // 设置2个post参数，一个是scope 一个是q
        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
        parameters.add(new BasicNameValuePair("scope","project"));
        parameters.add(new BasicNameValuePair("q","java"));
        //构造一个form表单式的实体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
        // 将请求实体设置到httpPost对象中
        httpPost.setEntity(formEntity);
        // 伪装浏览器
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = null;
        String content = null;
        try{
            // 执行请求
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode()==200){
                content = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }finally{
            if (response != null){
                response.close();
            }
            httpclient.close();
            return content;
        }
    }
    public String doPostJson() throws Exception{
        final String CONTENT_TYPE_TEXT_JSON = "text/json";
        return CONTENT_TYPE_TEXT_JSON;
    }
}
