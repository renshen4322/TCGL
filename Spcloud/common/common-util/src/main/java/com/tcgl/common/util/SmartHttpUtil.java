package com.tcgl.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http请求工具类
 *
 * @author Shuguang_Liux
 * @date 2021/11/08 17:01
 */
public class SmartHttpUtil {

    /**
     * 发得到
     * 方法描述: 发送get请求
     *
     * @param url    url
     * @param params 参数个数
     * @param header 头
     * @return {@link String}
     * @throws Exception 异常
     * @date 2020年07月27日 09:10:10
     */
    public static String sendGet(String url, Map<String, String> params, Map<String, String> header) throws Exception {
        HttpGet httpGet = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            List<String> mapList = new ArrayList<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    mapList.add(entry.getKey() + "=" + entry.getValue());
                }
            }
            if (CollectionUtils.isNotEmpty(mapList)) {
                url = url + "?";
                String paramsStr = StringUtils.join(mapList, "&");
                url = url + paramsStr;
            }
            httpGet = new HttpGet(url);
            httpGet.setHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = httpClient.execute(httpGet);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpGet != null) {
                httpGet.releaseConnection();
            }
        }
        return body;
    }

    /**
     * 发帖子json
     * 方法描述: 发送post请求-json数据
     *
     * @param url    url
     * @param json   json
     * @param header 头
     * @return {@link String}
     * @throws Exception 异常
     * @date 2020年07月27日 09:10:54
     */
    public static String sendPostJson(String url, String json, Map<String, String> header) throws Exception {
        HttpPost httpPost = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            StringEntity entity = new StringEntity(json, StandardCharsets.UTF_8);
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return body;
    }

    /**
     * 发送post表单
     * 方法描述: 发送post请求-form表单数据
     *
     * @param url    url
     * @param params 参数个数
     * @param header 头
     * @return {@link String}
     * @throws Exception 异常
     * @date 2020年07月27日 09:10:54
     */
    public static String sendPostForm(String url, Map<String, String> params, Map<String, String> header) throws Exception {
        HttpPost httpPost = null;
        String body = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            List<NameValuePair> nvps = new ArrayList<>();
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                throw new RuntimeException("请求失败");
            } else {
                body = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return body;
    }

}
