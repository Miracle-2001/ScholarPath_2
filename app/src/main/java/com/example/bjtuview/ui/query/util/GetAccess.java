package com.example.bjtuview.ui.query.util;

import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class GetAccess {

    public interface TokenCallback {
        void onTokenReceived(String token);
    }

    public static void getAuth(String ak, String sk, TokenCallback callback) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                // 获取token地址
                String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
                String getAccessTokenUrl = authHost
                        // 1. grant_type为固定参数
                        + "grant_type=client_credentials"
                        // 2. 官网获取的 API Key
                        + "&client_id=" + ak
                        // 3. 官网获取的 Secret Key
                        + "&client_secret=" + sk;
                try {
                    URL realUrl = new URL(getAccessTokenUrl);
                    // 打开和URL之间的连接
                    HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    Map<String, List<String>> map = connection.getHeaderFields();
                    //定义 BufferedReader输入流来读取URL的响应
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String result = "";
                    String line;
                    while ((line = in.readLine()) != null) {
                        result += line;
                    }
                    /**
                     * 返回结果示例
                     */
                    JsonObject jsonObject = (JsonObject)new JsonParser().parse(result);
                    String access_token = jsonObject.get("access_token").toString();
                    return access_token;
                } catch (Exception e) {
                    System.err.printf("获取token失败！");
                    e.printStackTrace(System.err);
                }
                return null;
            }

            @Override
            protected void onPostExecute(String token) {
                super.onPostExecute(token);
                // 在这里处理返回的结果
                if (callback != null) {
                    callback.onTokenReceived(token);
                }
            }
        }.execute();
    }
}

