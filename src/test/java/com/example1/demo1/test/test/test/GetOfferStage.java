package com.example1.demo1.test.test.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetOfferStage {
    public static void main(String[] arg){
        System.out.println("ok");

//        HttpPostUtils hp = new HttpPostUtils();
//        JSONObject object2 = new JSONObject();
//        String str = "{\"pipelineId\":\"550\",\"stageId\":\"3506\",\"outerStage\":\"0\",\"jobPreference\":\"all\",\"jobStatus\":[\"open\"],\"jobIds\":[],\"enableAiFilter\":false,\"limit\":30}";
//        //先将这条数据解析为JSONObject
//        JSONObject outJson = JSONObject.parseObject(str);
//        String urld = "https://app-tc.mokahr.com/api/applications";
//        String data = hp.doPost(urld,str,null,null);
        test100();
//        System.out.println(data);

    }

    public static void test100(){
        String str = "{\"pipelineId\":[{\"stageId\":\"3506\"},{\"stageId\":\"3507\"}],\"stageId\":\"3506\",\"outerStage\":\"0\",\"jobPreference\":\"all\",\"jobStatus\":[\"open\"],\"jobIds\":[],\"enableAiFilter\":false,\"limit\":30}";

        JSONObject jsonObject = JSON.parseObject(str.toString());
        JSONArray jsonArray = jsonObject.getJSONArray("pipelineId");
        Iterator<Object> iterator = jsonArray.iterator();
        List<Object> habits2 = new JSONArray();
        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            habits2.add(object.get("stageId"));
        }
        String habits3 = JSONArray.toJSONString(habits2);
        System.out.println(habits3);
    }

}




/**
 * http请求工具类
 * @author ouyangjun
 */
class HttpPostUtils {

    /**
     * http post请求, 带参数
     * @param requestURL
     * @param params
     * @return
     */
    public static String doPost(String requestURL, String params, String proxyHost, Integer proxyPort) {
        // 记录信息
        StringBuffer buffer = new StringBuffer();

        HttpURLConnection conn = null;
        try {
            URL url = new URL(requestURL);
            // 判断是否需要代理模式请求http
            if (proxyHost != null && proxyPort != null) {
                // 如果是本机自己测试, 不需要代理请求,但发到服务器上的时候需要代理请求
                // 对http开启全局代理
                //System.setProperty("http.proxyHost", proxyHost);
                //System.setProperty("http.proxyPort", proxyPort);
                // 对https开启全局代理
                //System.setProperty("https.proxyHost", proxyHost);
                //System.setProperty("https.proxyPort", proxyPort);

                // 代理访问http请求
                Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) url.openConnection(proxy);
            } else {
                // 原生访问http请求，未代理请求
                conn = (HttpURLConnection) url.openConnection();
            }

            // 设置请求的属性
            conn.setDoOutput(true); // 是否可以输出
            conn.setRequestMethod("POST"); // 请求方式, 只包含"GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"六种
            conn.setConnectTimeout(60000); // 最高超时时间
            conn.setReadTimeout(60000); // 最高读取时间
            conn.setConnectTimeout(60000); // 最高连接时间

            conn.setDoInput(true); // 是否可以输入
            if (params != null) {
                // 设置参数为json格式
                conn.setRequestProperty("Content-type", "application/json");
                String cookied = "__snaker__id=e2V38Z2lUPl8cP06; gdxidpyhxdE=XNAoGxsUJQ8vxOcXXs1K9tyCvyBI%5Cgy5rtAcdRU0IHKAuGQY1%2BiVLdNpDxWff0keMZz1zyY9%2BuyooVhgkRwdZOgPhSpo05blvDOGX4i3aSp%2FjsGEE7rniQfonciU0aIy3XhShdMZ1WGn43y8Q6IBETeu82o7c9xPgXHIbY94jx4N9O7b%3A1618215327204; _9755xjdesxxd_=32; locale=zh-CN; AIPortal_Res_Account=yangzhiyong%40mokahr.com; connect.sid=s%3A5gTOj6PVW5XYogiiqJue61sfobB2nHfj.ro28Bbw59GWXQsCZmC23xKcLzmJTIfnaBhscQVaKaiE; user_email=yangzhiyong%40mokahr.com; moka-jwt=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5YzE4YWIwYzU3NzQ0NGFhOGIxMTI1YTY5MDAyYzA0YyIsImlzcyI6Im1va2EiLCJhdWQiOiJtYWdlIiwiaWF0IjoxNjE5NTk1NzE1LCJzdWIiOiJ7XCJidXNcIjoxMCxcImF0c1VpZFwiOlwiMTAwMDEwMDA4XCIsXCJhdHNPcmdJZFwiOlwieGlhb21pXCJ9IiwiZXhwIjoxNjIxMDY2OTQ0fQ.9dTebsASXY23nkXMZyVlqpAVYf-3cNo0RcVVukvy7Qc; moka-token=De3mTSsbb2lqKt5H5SEWi8RiqURmr86a4N3xaBtCs8iEBh2ik06mClCmnkTssE5R; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22100010008%22%2C%22%24device_id%22%3A%22178ef478d1993b-07cdcdcf051deb-1f3b6054-3686400-178ef478d1aebd%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%2C%22first_id%22%3A%22178ef478d1993b-07cdcdcf051deb-1f3b6054-3686400-178ef478d1aebd%22%7D";
                conn.setRequestProperty("cookie", cookied);
                String userd = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36";
                conn.setRequestProperty("user-agent", userd);

                // 写入参数信息
                OutputStream os = conn.getOutputStream();
                try {
                    os.write(params.getBytes("UTF-8"));

                } catch (Exception e) {
                    System.out.println("HttpPostUtils doPost error: " + e);
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {
                        System.out.println("HttpPostUtils doPost error: " + e);
                    }
                }
            }

            // 读取数据
            InputStream is = null;
            InputStreamReader inputReader = null;
            BufferedReader reader = null;
            try {
                is = conn.getInputStream();
                inputReader = new InputStreamReader(is, "UTF-8");
                reader = new BufferedReader(inputReader);

                String temp;
                while ((temp = reader.readLine()) != null) {
                    buffer.append(temp);
                }
            } catch (Exception e) {
                System.out.println("HttpPostUtils doPost error: " + e);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }

                    if (inputReader != null) {
                        inputReader.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    System.out.println("HttpPostUtils doPost error: " + e);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 当http连接空闲时, 释放资源
            if (conn != null) {
                conn.disconnect();
            }
        }
        // 返回信息
        return buffer.length()==0 ? "" : buffer.toString();
    }


}